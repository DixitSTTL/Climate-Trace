package com.app.climate_trace.businesslogic.viewmodel.fragment

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.climate_trace.businesslogic.pojo.emission.CountryData
import com.app.climate_trace.businesslogic.viewmodel.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelEmission @Inject constructor() : ViewModelBase() {

    private var _dataList: MutableLiveData<List<CountryData>> = MutableLiveData()
    val dataList: LiveData<List<CountryData>> = _dataList
    val observeCountryCode = ObservableField<String>()
    val observeCountryName= ObservableField<String>()

    fun fetchApi() {
        isDataLoaded.set(false)
        _dataList.value = emptyList()
        viewModelScope.launch {
            var data = _apiHelper.fetchEmissionOfCountry(observeCountryCode.get())?.body()

            data?.let {

                _dataList.value = it.get(observeCountryCode.get())
                isDataLoaded.set(true)

            }
        }
    }
}