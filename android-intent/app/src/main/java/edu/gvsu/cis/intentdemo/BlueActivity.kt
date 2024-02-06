package edu.gvsu.cis.intentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.gvsu.cis.intentdemo.databinding.ActivityBlueBinding

class BlueActivity : AppCompatActivity() {
    lateinit var binding: ActivityBlueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.doneBtn.setOnClickListener {
            finish()
        }
    }
}