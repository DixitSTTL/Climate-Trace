package com.app.coroutinedemo.views.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.app.coroutinedemo.R
import com.app.coroutinedemo.businesslogic.viewmodel.activity.ViewmodelMain
import com.app.coroutinedemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class ActivityMain : ActivityBase(), NavController.OnDestinationChangedListener {

    private lateinit var navController: NavController
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: ViewmodelMain
    private var mDrawerToggle: ActionBarDrawerToggle? = null

    var press_time: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mBinding = DataBindingUtil.setContentView(this@ActivityMain, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mViewModel = ViewModelProvider(this@ActivityMain)[ViewmodelMain::class.java]
        mBinding.mViewModel = mViewModel

        setToolbar()
        setDrawer()
        setNavigator()

    }

    private fun setDrawer() {
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,
            mBinding.drawerLayoutMain,
            mBinding.toolbar,
            R.string.navigationDrawerOpen,
            R.string.navigationDrawerClose
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }
        mDrawerToggle!!.toolbarNavigationClickListener = View.OnClickListener { v: View? ->
            onBackPressed()
        }
        mBinding.drawerLayoutMain.addDrawerListener(mDrawerToggle!!)
    }

    private fun setNavigator() {
        navController = Navigation.findNavController(this, R.id.frame)
        navController.addOnDestinationChangedListener(this)
    }

    private fun setToolbar() {
        setSupportActionBar(mBinding!!.toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }
    }

    override fun onDestinationChanged(
        controller: NavController, destination: NavDestination, arguments: Bundle?
    ) {
        setToolBarTitle(destination.label)
        setToolBarHome(destination.id == R.id.fragmentDashboard)

    }

    override fun onNavigateUp(): Boolean {
        navController.popBackStack()
        return super.onNavigateUp()
    }


    private fun setToolBarTitle(label: CharSequence?) {
        mBinding.toolbar.title = label
    }

    private fun setToolBarHome(isRoot: Boolean) {
        mDrawerToggle?.isDrawerIndicatorEnabled = isRoot
        supportActionBar?.setDisplayShowHomeEnabled(!isRoot)
        supportActionBar?.setDisplayHomeAsUpEnabled(!isRoot)
        supportActionBar?.setHomeButtonEnabled(!isRoot)
        setDrawerEnabled(isRoot)
        mBinding.toolbar.navigationIcon?.setTint(getColor(R.color.white));
        mDrawerToggle!!.syncState()

    }

    private fun setDrawerEnabled(enabled: Boolean) {
        val lockMode =
            if (enabled) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        mBinding!!.drawerLayoutMain.setDrawerLockMode(lockMode)
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