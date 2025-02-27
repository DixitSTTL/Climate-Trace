package com.app.climate_trace.businesslogic.viewmodel.fragment

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.climate_trace.businesslogic.pojo.countries.PojoCountriesItem
import com.app.climate_trace.businesslogic.pojo.emission.CountryData
import com.app.climate_trace.businesslogic.viewmodel.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelEmission @Inject constructor() : ViewModelBase() {

    private var _dataList: MutableLiveData<List<CountryData>> = MutableLiveData()
    val dataList: LiveData<List<CountryData>> = _dataList
    val dataCountry = ObservableField<PojoCountriesItem>()

    fun fetchApi() {
        isDataLoaded.set(false)
        _dataList.value = emptyList()
        viewModelScope.launch {
            var data = _apiHelper.fetchEmissionOfCountry(dataCountry.get()!!.alpha3)?.body()

            data?.let {

                _dataList.value = it?.get(dataCountry.get()!!.alpha3)
                isDataLoaded.set(true)

            }
        }
    }
}