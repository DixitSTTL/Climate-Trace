package com.app.coroutinedemo.views.activity

import androidx.appcompat.app.AppCompatActivity
import com.app.coroutinedemo.MyApplication
import com.app.coroutinedemo.businesslogic.network.ApiHelper
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class ActivityBase : AppCompatActivity() {

    @Inject
    protected lateinit var mApplication: MyApplication

    @Inject
    protected lateinit var mApiHelper: ApiHelper

    @Inject
    protected lateinit var mPreference: UtilsSharedPreferences

    open fun getStatusBarHeight(): Int {
        return try {
            var result = 0
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = resources.getDimensionPixelSize(resourceId)
            }
            result
        } catch (e: Exception) {
            0
        }
    }
}