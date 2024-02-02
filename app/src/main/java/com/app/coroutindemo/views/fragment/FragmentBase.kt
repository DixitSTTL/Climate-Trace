package com.app.coroutindemo.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.coroutindemo.MyApplication
import com.app.coroutindemo.businesslogic.network.ApiHelper
import com.app.coroutindemo.views.activity.ActivityMain
import dagger.hilt.android.AndroidEntryPoint
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