package com.app.climate_trace.businesslogic.viewmodel.fragment

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.climate_trace.businesslogic.pojo.countries.PojoCountries
import com.app.climate_trace.businesslogic.viewmodel.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCountries @Inject constructor() : ViewModelBase() {

    private var _dataList: MutableLiveData<PojoCountries> = MutableLiveData()
    val dataList: LiveData<PojoCountries> = _dataList
    var searchText = ObservableField<String>()

    fun fetchApi() {
        if (!isDataLoaded.get()) {

            viewModelScope.launch {
                var data = _apiHelper.fetchCountries()?.body()

                data?.let {
                    _dataList.value?.clear()
                    _dataList.value = it
                }
                isDataLoaded.set(true)
            }
        }
    }
}