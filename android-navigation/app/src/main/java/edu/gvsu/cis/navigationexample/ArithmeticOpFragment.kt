package edu.gvsu.cis.navigationexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass.
 * Use the [ArithmeticOpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ArithmeticOpFragment : Fragment() {
    val inputArgs: ArithmeticOpFragmentArgs by navArgs()
    lateinit var label:TextView
    lateinit var addBtn:Button
    lateinit var subBtn:Button
    lateinit var navCtrl:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arithmetic_op, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navCtrl = findNavController()
        requireActivity().apply {
            label = findViewById<TextView>(R.id.text_label)
            addBtn = findViewById<Button>(R.id.add_btn)
            subBtn = findViewById<Button>(R.id.subtract_btn)
        }
        addBtn.setOnClickListener {
            val result = bundleOf("op" to "addition",
                "value" to inputArgs.first + inputArgs.second)
            navCtrl.previousBackStackEntry
                ?.savedStateHandle
                ?.set("result", result)
            navCtrl.popBackStack()
        }
        subBtn.setOnClickListener {
            val result = bundleOf("op" to "subtraction",
                "value" to inputArgs.first - inputArgs.second)
            navCtrl.previousBackStackEntry
                ?.savedStateHandle
                ?.set("result", result)
            navCtrl.popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        label.text = "What to do with ${inputArgs.first} and ${inputArgs.second}"

        // Remove previously set result so the observer does not fire multiple times
        navCtrl.previousBackStackEntry?.savedStateHandle?.let {
            if (it.contains("result")) it.remove<Bundle>("result")
        }
    }

    companion object {
    }
}