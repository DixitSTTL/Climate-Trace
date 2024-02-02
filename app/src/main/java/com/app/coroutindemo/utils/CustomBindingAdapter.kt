package com.app.coroutindemo.utils

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class CustomBindingAdapter {
    companion object{

        @JvmStatic
        @BindingAdapter("bindAdapterSector")
        fun bindAdapterSector(recyclerView: RecyclerView,dataList:List<String>?){
            Log.d("dataList-","  "+dataList)

            dataList?.forEach {
                Log.d("dataList","  "+it)
            }
        }
    }
}