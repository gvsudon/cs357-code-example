package edu.gvsu.cis.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    val vm: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val uInfo = findViewById<TextView>(R.id.userInfo)
        vm.uid.observe(this) {
            it?.let {
                uInfo.text = "Your UID is $it"
            }
        }
        vm.persons.observe(this) {
            if (it.size > 0)
                Snackbar.make(uInfo, "${it.size} items read", Snackbar.LENGTH_LONG)
                    .show()
        }
        findViewById<Button>(R.id.logoutBtn).setOnClickListener {
            finish()
            vm.logout()
        }
    }

    override fun onResume() {
        super.onResume()
//        vm.fetchMembers()
    }
}