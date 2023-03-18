package edu.gvsu.cis.navigationexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

class NumberInputFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appNav = findNavController()
        val input1: EditText
        val input2: EditText
        requireActivity().apply {
            input1 = findViewById<EditText>(R.id.firstNum)
            input2 = findViewById<EditText>(R.id.secondNum)
        }
        requireActivity().findViewById<Button>(R.id.arithmetic_btn).apply {
            setOnClickListener {
                println("Button is pressed?")
                val first = input1.text.toString().toInt()
                val second = input2.text.toString().toInt()
                val action = NumberInputFragmentDirections.toArithOp(first, second)
                appNav.navigate(action)
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