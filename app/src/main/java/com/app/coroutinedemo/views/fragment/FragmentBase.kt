package com.app.coroutinedemo.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.coroutinedemo.MyApplication
import com.app.coroutinedemo.businesslogic.network.ApiHelper
import com.app.coroutinedemo.views.activity.ActivityMain
import javax.inject.Inject

open class FragmentBase : Fragment() {

    @Inject
    protected lateinit var apiHelper: ApiHelper

    @Inject
    protected lateinit var mApplication: MyApplication

    protected var mActivityMain: ActivityMain? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mActivityMain = activity as ActivityMain?

    }

}