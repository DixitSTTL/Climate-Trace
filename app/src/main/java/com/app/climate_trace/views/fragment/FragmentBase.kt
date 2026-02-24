package com.app.climate_trace.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.climate_trace.MyApplication
import com.app.climate_trace.businesslogic.network.ApiHelper
import com.app.climate_trace.views.activity.ActivityMain
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import javax.inject.Inject

open class FragmentBase : Fragment() {

    @Inject
    lateinit var apiHelper: ApiHelper

    @Inject
    lateinit var mApplication: MyApplication

    @Inject
    lateinit var mPreferences: UtilsSharedPreferences

    var mActivityMain: ActivityMain? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mActivityMain = activity as ActivityMain?

    }

}