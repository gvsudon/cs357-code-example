package edu.gvsu.cis.navigationexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass.
 * Use the [ArithmeticOpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ArithmeticOpFragment : Fragment() {
    val inputArgs: ArithmeticOpFragmentArgs by navArgs()
    lateinit var label:TextView
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
        requireActivity().apply {
            label = findViewById<TextView>(R.id.text_label)
        }
    }

    override fun onResume() {
        super.onResume()
        label.text = "What to do with ${inputArgs.first} and ${inputArgs.second}"
    }

    companion object {
    }
}