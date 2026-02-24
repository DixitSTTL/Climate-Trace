package com.app.climate_trace.businesslogic.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.app.climate_trace.MyApplication
import com.app.climate_trace.businesslogic.network.ApiHelper
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class ViewModelBase @Inject constructor() : ViewModel() {

    @Inject
     lateinit var _apiHelper: ApiHelper

    @Inject
     lateinit var mApplication: MyApplication

    @Inject
     lateinit var mPreferences: UtilsSharedPreferences

    val isDataLoaded = ObservableBoolean(false)
    val isDarkModeEnable = ObservableBoolean(false)
    val observableSwipeRefreshing = ObservableBoolean(false)
    val observableEnabledSwipeRefresh = ObservableBoolean(true)

}