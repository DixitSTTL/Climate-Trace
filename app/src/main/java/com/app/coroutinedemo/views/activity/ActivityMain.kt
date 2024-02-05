package com.app.coroutinedemo.views.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.app.coroutinedemo.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class ActivityMain : ActivityBase(), NavController.OnDestinationChangedListener {

    private lateinit var navController: NavController
    var press_time: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navController = Navigation.findNavController(this, R.id.frame)
        navController.addOnDestinationChangedListener(this)

    }

    override fun onDestinationChanged(
        controller: NavController, destination: NavDestination, arguments: Bundle?
    ) {


    }

    override fun onBackPressed() {
        if (navController.popBackStack()) {
            // Handle custom behavior if needed
        } else {
            if (press_time > Calendar.getInstance().timeInMillis) {
                super.onBackPressed()
            } else {
                press_time = Calendar.getInstance().timeInMillis + 2000
                Toast.makeText(this, "press again to back", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun navigateToContinents() {

        navController.navigate(R.id.action_fragmentDashboard_to_fragmentContinents)
    }

    fun navigateToCountries() {

        navController.navigate(R.id.action_fragmentDashboard_to_fragmentCountries)
    }

    fun navigateToSectors() {

        navController.navigate(R.id.action_fragmentDashboard_to_fragmentSectors)
    }

    fun navigateToSubSectors() {

        navController.navigate(R.id.action_fragmentDashboard_to_fragmentSubSectors)
    }

    fun navigateToGases() {

        navController.navigate(R.id.action_fragmentDashboard_to_fragmentGases)
    }

    fun navigateToEmission(action: NavDirections) {

        navController.navigate(action)
    }

}