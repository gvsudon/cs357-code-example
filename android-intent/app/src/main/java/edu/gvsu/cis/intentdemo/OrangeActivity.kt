package edu.gvsu.cis.intentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.gvsu.cis.intentdemo.databinding.ActivityBlueBinding
import edu.gvsu.cis.intentdemo.databinding.ActivityOrangeBinding

class OrangeActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrangeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.orangeDoneBtn.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        val stockName = intent.getStringExtra("stock")
        val stockAmount = intent.getIntExtra("unit", 0)
        binding.info.text = "Buy $stockAmount units of  $stockName stock"
    }
}