package com.app.coroutinedemo.businesslogic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.coroutinedemo.MyApplication
import com.app.coroutinedemo.businesslogic.network.ApiHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class ViewModelBase @Inject constructor() : ViewModel() {

    @Inject
    protected lateinit var apiHelper: ApiHelper

    @Inject
    protected lateinit var mApplication: MyApplication

    val isDataLoaded = MutableLiveData(false)

}