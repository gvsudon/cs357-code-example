package edu.gvsu.cis.navigationexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

class NumberInputFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appNav = findNavController()
        val input1: EditText
        val input2: EditText
        val arithmeticBtn: Button
        requireActivity().apply {
            input1 = findViewById<EditText>(R.id.firstNum)
            input2 = findViewById<EditText>(R.id.secondNum)
            arithmeticBtn = findViewById<Button>(R.id.arithmetic_btn)
            arithmeticBtn.isEnabled = false
        }
        input1.addTextChangedListener {
            arithmeticBtn.isEnabled =
                input2.text.toString().length > 0 && input2.text.toString().length > 0
        }
        input2.addTextChangedListener {
            arithmeticBtn.isEnabled =
                input2.text.toString().length > 0 && input2.text.toString().length > 0
        }
        arithmeticBtn.setOnClickListener {
            println("Button is pressed?")
            val first = input1.text.toString().toInt()
            val second = input2.text.toString().toInt()
            val action = NumberInputFragmentDirections.toArithOp(first, second)
            appNav.navigate(action)
        }
        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Bundle>("result")
            ?.observe(viewLifecycleOwner) {
                val numeric = it.getInt("value", 0)
                it.getString("op")?.let {
                    Snackbar
                        .make(input1, "Result of $it is $numeric", Snackbar.LENGTH_LONG)
                        .show()
                }
            }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number_input, container, false)
    }

    companion object {
    }
}