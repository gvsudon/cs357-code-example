package edu.gvsu.cis.intentdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.gvsu.cis.intentdemo.databinding.ActivityYellowBinding

class YellowActivity : AppCompatActivity() {
    lateinit var binding: ActivityYellowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYellowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.confirmBtn.setOnClickListener {
            val confirmIntent = Intent()
            confirmIntent.putExtra("confirmation", "KTZM2390")
            setResult(RESULT_OK, confirmIntent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        val stockName = intent.getStringExtra("stock")
        val stockAmount = intent.getIntExtra("unit", 0)
        binding.yellowInfo.text = "Buy $stockAmount units of  $stockName stock"
    }
}