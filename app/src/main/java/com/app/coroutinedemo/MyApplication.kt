package com.app.coroutinedemo

import android.app.Application
import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.app.coroutinedemo.utils.Constants
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {
    @Inject
    lateinit var mPreferences: UtilsSharedPreferences
    override fun onCreate() {
        super.onCreate()
        init()
    }

    fun init() {
        val isEnable = mPreferences.getBooleanDefault(
            Constants.SettingsConstantCodes.IS_DARK_MODE_ENABLED,
            isSystemInDarkMode(this)
        )
        AppCompatDelegate.setDefaultNightMode(if (isEnable) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)

    }


    fun isSystemInDarkMode(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as? UiModeManager

            uiModeManager?.nightMode == AppCompatDelegate.MODE_NIGHT_YES
        } else {
            @Suppress("DEPRECATION")
            val currentNightMode =
                context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

            currentNightMode == Configuration.UI_MODE_NIGHT_YES

        }
    }

}