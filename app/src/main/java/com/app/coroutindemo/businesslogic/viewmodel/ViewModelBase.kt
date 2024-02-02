package com.app.coroutindemo.businesslogic.viewmodel

import androidx.lifecycle.ViewModel
import com.app.coroutindemo.MyApplication
import com.app.coroutindemo.businesslogic.network.ApiHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class ViewModelBase @Inject constructor():ViewModel() {

    @Inject
    protected lateinit var apiHelper: ApiHelper

    @Inject
    protected lateinit var mApplication: MyApplication

}