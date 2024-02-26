package edu.gvsu.cis.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    val vm: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nameList = findViewById<RecyclerView>(R.id.name_list)
        nameList.adapter = NameAdapter(vm.persons.value!!)
        nameList.layoutManager = LinearLayoutManager(this)
        val uInfo = findViewById<TextView>(R.id.userInfo)
        vm.uid.observe(this) {
            it?.let {
                uInfo.text = "Your UID: $it"
            }
        }
        vm.persons.observe(this) {
            uInfo.text = "UID ${vm.uid.value} (${vm.persons.value!!.size}  names)"
            if (it.isNotEmpty()) {
                nameList.adapter!!.notifyDataSetChanged()
                Snackbar.make(uInfo, "${it.size} items read", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
        findViewById<Button>(R.id.add_one).setOnClickListener {
            vm.addOne()
        }
        findViewById<Button>(R.id.logoutBtn).setOnClickListener {
            finish()
            vm.logout()
        }
    }

    override fun onResume() {
        super.onResume()
        vm.fetchMembers()
    }
}