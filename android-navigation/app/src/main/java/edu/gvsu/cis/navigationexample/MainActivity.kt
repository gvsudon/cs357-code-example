package edu.gvsu.cis.navigationexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    lateinit var navCtrl: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Enable Back button as in iOS style
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navCtrl = navHost.navController
        setupActionBarWithNavController(navCtrl)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navCtrl.navigateUp() || super.onSupportNavigateUp()
    }

}