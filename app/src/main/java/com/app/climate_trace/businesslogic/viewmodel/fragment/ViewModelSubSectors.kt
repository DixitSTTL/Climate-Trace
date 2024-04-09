package com.app.climate_trace.businesslogic.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.climate_trace.businesslogic.viewmodel.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSubSectors @Inject constructor() : ViewModelBase() {

    private var _dataList: MutableLiveData<ArrayList<String>> = MutableLiveData()
    val dataList: LiveData<ArrayList<String>> = _dataList
    var onRefreshListener = SwipeRefreshLayout.OnRefreshListener { refreshSubSectors() }

    private fun refreshSubSectors() {
        observableSwipeRefreshing.set(true)
        isDataLoaded.set(false)
        fetchApi()
    }

    fun fetchApi() {
        if (!isDataLoaded.get()) {

            viewModelScope.launch {
                var data = _apiHelper.fetchSubSectors()?.body()

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