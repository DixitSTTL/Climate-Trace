package com.app.coroutinedemo.businesslogic.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.app.coroutinedemo.MyApplication
import com.app.coroutinedemo.businesslogic.network.ApiHelper
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class ViewModelBase @Inject constructor() : ViewModel() {

    @Inject
    protected lateinit var apiHelper: ApiHelper

    @Inject
    protected lateinit var mApplication: MyApplication

    @Inject
    protected lateinit var mPreferences: UtilsSharedPreferences

    val isDataLoaded = ObservableBoolean(false)
    val isDarkModeEnable = ObservableBoolean(false)
    val observableSwipeRefreshing = ObservableBoolean(false)
    val observableEnabledSwipeRefresh = ObservableBoolean(true)

}