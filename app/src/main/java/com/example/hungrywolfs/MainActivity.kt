package com.example.hungrywolfs

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hungrywolfs.databinding.ActivityMainBinding
import com.example.hungrywolfs.fragments.NoInternetConnectionFragment
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.fixedRateTimer


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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(destinationChangedListener)

        binding.bottomNavigationView.setupWithNavController(navController)

        fixedRateTimer("timer", false, 0, 1000) {
           runOnUiThread {
               checkConnection()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun checkConnection(){
        if(isOnline()){
            if( findNavController(R.id.fragmentContainerView).currentBackStackEntry?.destination?.id == R.id.noInternetConnectionFragment)
                findNavController(R.id.fragmentContainerView).popBackStack()
        }
        else {
            findNavController(R.id.fragmentContainerView).navigate(R.id.noInternetConnectionFragment)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = cm.activeNetwork
        val activeNetwork = cm.getNetworkCapabilities(networkCapabilities)

        return when {
            activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> true
            activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> true
            else -> false
        }
    }
}