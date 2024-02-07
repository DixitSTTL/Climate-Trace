package com.app.coroutinedemo.views.activity

import android.app.Activity
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import com.app.coroutinedemo.MyApplication
import com.app.coroutinedemo.businesslogic.network.ApiHelper
import javax.inject.Inject

open class ActivityBase : AppCompatActivity() {

    @Inject
    protected lateinit var mApplication: MyApplication

    @Inject
    protected lateinit var mApiHelper: ApiHelper

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