package com.app.coroutindemo.views.activity

import androidx.appcompat.app.AppCompatActivity
import com.app.coroutindemo.MyApplication
import com.app.coroutindemo.businesslogic.network.ApiHelper
import javax.inject.Inject

open class ActivityBase : AppCompatActivity() {

    @Inject
    protected lateinit var mApplication: MyApplication

    @Inject
    protected lateinit var mApiHelper: ApiHelper


}