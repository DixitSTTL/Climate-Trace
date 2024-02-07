package com.app.coroutinedemo.businesslogic.viewmodel.activity

import androidx.databinding.ObservableField
import com.app.coroutinedemo.businesslogic.viewmodel.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewmodelMain @Inject constructor() : ViewModelBase() {

    var observableToolbarTitle = ObservableField<String>()


}