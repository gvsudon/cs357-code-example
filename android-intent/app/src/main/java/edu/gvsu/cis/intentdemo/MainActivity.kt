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

        var stockName = binding.stockName.text.toString()
        var stockQty = binding.stockQuantity.text.toString().toInt()

        /* Transition to BLUE NO DATA */
        binding.noData.setOnClickListener {
            val toBlue = Intent(this, BlueActivity::class.java)

            startActivity(toBlue)
        }

        /* Transition to ORANGE 1-WAY */
        binding.withDataOneway.setOnClickListener {
            val toOrange = Intent(this, OrangeActivity::class.java)
            toOrange.putExtra("stock", stockName)
            toOrange.putExtra("unit", stockQty)

            startActivity(toOrange)
        }

        /* Transition to YELLOW 2-WAY */
        binding.withDataTwoWay.setOnClickListener {
            val toYellow = Intent(this, YellowActivity::class.java)
            toYellow.putExtra("stock", stockName)
            toYellow.putExtra("unit", stockQty)

            yellowLauncher.launch(toYellow)
        }
    }
}