package com.app.coroutinedemo.businesslogic.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.coroutinedemo.businesslogic.viewmodel.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewmodelSectors @Inject constructor() : ViewModelBase() {

    private var _dataList: MutableLiveData<ArrayList<String>> = MutableLiveData()
    val dataList: LiveData<ArrayList<String>>
        get() = _dataList

    fun fetchApi() {
        if (!isDataLoaded.value!!) {

            viewModelScope.launch {
                var data = apiHelper.fetchSectors().body()

                data.let {
                    _dataList.value?.clear()
                    _dataList.value = it
                }
                isDataLoaded.value = true
            }
        }
    }

    fun deleteItem(s: String) {
        viewModelScope.launch {
            _dataList.value?.remove(s)

        }

    }
}