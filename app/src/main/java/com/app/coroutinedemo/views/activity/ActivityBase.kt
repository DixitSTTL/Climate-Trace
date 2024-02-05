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


}