package com.example.hungrywolfs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hungrywolfs.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    private val destinationChangedListener = NavController.OnDestinationChangedListener{_, destination, _ ->
        val bottomNavVisibility = when (destination.id) {
            R.id.homeFragment, R.id.favouritesFragment, R.id.profileFragment -> true
            else -> false
        }
        binding.bottomNavigationView.visibility =
            if (bottomNavVisibility) View.VISIBLE else View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(destinationChangedListener)

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}