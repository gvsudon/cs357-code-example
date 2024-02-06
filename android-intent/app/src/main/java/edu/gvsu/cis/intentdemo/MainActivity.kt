package edu.gvsu.cis.intentdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import edu.gvsu.cis.intentdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val yellowLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val confCode = it.data?.getStringExtra("confirmation")
                Snackbar.make(
                    binding.root,
                    "Your confirmation code is $confCode",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.noData.setOnClickListener {
            val toBlue = Intent(this, BlueActivity::class.java)
            startActivity(toBlue)
        }
        binding.withDataOneway.setOnClickListener {
            val toOrange = Intent(this, OrangeActivity::class.java)
            toOrange.putExtra("stock", "APPL")
            toOrange.putExtra("unit", 300)
            startActivity(toOrange)
        }

        binding.withDataTwoWay.setOnClickListener {
            val toYellow = Intent(this, YellowActivity::class.java)
            toYellow.putExtra("stock", "APPL")
            toYellow.putExtra("unit", 300)


            yellowLauncher.launch(toYellow)
        }
    }
}