package edu.gvsu.cis.navigationexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ArithmeticActivity : AppCompatActivity() {
    lateinit var label:TextView
     var input1 = 0
     var input2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arithmetic)

        label = findViewById<TextView>(R.id.text_label)
        val addBtn = findViewById<Button>(R.id.add_btn)
        addBtn.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("result", input1 + input2)
            setResult(Activity.RESULT_OK, resultIntent)
            this.finish()
        }
    }

    override fun onResume() {
        super.onResume()
        input1 = intent.getIntExtra("first", 0)
        input2 = intent.getIntExtra("second", 0)
        label.text = "Select arithmetic operation on $input1 and $input2"
    }
}