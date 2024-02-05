package com.app.coroutinedemo.businesslogic.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.coroutinedemo.businesslogic.viewmodel.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelContinents @Inject constructor() : ViewModelBase() {

    private var _dataList: MutableLiveData<ArrayList<String>> = MutableLiveData()
    val dataList: LiveData<ArrayList<String>> = _dataList
    var onRefreshListener = SwipeRefreshLayout.OnRefreshListener { refreshContinents() }

    private fun refreshContinents() {
        observableSwipeRefreshing.set(true)
        isDataLoaded.set(false)
        fetchApi()
    }
    fun fetchApi() {
        if (!isDataLoaded.get()) {

            viewModelScope.launch {
                var data = apiHelper.fetchContinents().body()

                data.let {
                    _dataList.value?.clear()
                    _dataList.value = it
                }
                observableSwipeRefreshing.set(false)
                isDataLoaded.set(true)
            }
        }
    }
    fun deleteItem(s: String) {
        viewModelScope.launch {
            _dataList.value?.remove(s)
            _dataList.postValue(_dataList.value)
        }

    }
}