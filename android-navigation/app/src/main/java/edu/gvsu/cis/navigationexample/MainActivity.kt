package edu.gvsu.cis.navigationexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var input1:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val doArith = findViewById<Button>(R.id.arithmetic_btn)

        input1 = findViewById<EditText>(R.id.firstNum)
        val input2 = findViewById<EditText>(R.id.secondNum)

        doArith.setOnClickListener {
            val arithOp = Intent(this, ArithmeticActivity::class.java)
            input1.text.toString().toIntOrNull()?.let {

            }
            arithOp.putExtra("first", input1.text.toString().toIntOrNull() ?:  0)
            arithOp.putExtra("second", input2.text.toString().toIntOrNull() ?: 0)
            startActivityForResult(arithOp, 0xBeef)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            val arithResult = it.getIntExtra("result", 0)
            Snackbar.make(input1, "Arithmetic result is $arithResult", Snackbar.LENGTH_LONG)
                .show()
            println("Result of arithmetic is $arithResult")
        }

    }
}