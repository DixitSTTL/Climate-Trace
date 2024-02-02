package com.app.coroutindemo.businesslogic.viewmodel.fragment

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.coroutindemo.businesslogic.viewmodel.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ViewmodelSectors @Inject constructor(): ViewModelBase() {

     var dataList: ObservableArrayList<String> = ObservableArrayList()


    fun fetchApi(){
        viewModelScope.launch {
            var data= apiHelper.fetchSectors().body()
            Log.d("mvdkfb"," "+ data!!.size)

            data.let {
                dataList.addAll(it)
            }
        }
    }
}