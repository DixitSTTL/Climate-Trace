package com.app.coroutinedemo.businesslogic.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.coroutinedemo.businesslogic.viewmodel.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelGases @Inject constructor() : ViewModelBase() {
    var myLive= liveData {
        var data = apiHelper.fetchGases()?.body()
        data?.let {
            observableSwipeRefreshing.set(false)
            isDataLoaded.set(true)
            emit(it)
        }
    }

    private var _dataList: MutableLiveData<ArrayList<String>> = MutableLiveData()
    val dataList: LiveData<ArrayList<String>>
    get() = _dataList
    var onRefreshListener = SwipeRefreshLayout.OnRefreshListener { refreshGases() }



    private fun refreshGases() {
        observableSwipeRefreshing.set(true)
        isDataLoaded.set(false)
        fetchApi()
    }


    fun fetchApi() {
        if (!isDataLoaded.get()) {

            viewModelScope.launch {
                var data = apiHelper.fetchGases()?.body()

                data?.let {
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

    fun addItem(poition: Int, s: String?) {
        viewModelScope.launch {
            s?.let {
                _dataList.value?.add(poition, it)
                _dataList.postValue(_dataList.value)
            }

        }

    }
}