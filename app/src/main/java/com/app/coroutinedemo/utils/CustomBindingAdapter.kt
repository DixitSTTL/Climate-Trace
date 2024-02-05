package com.app.coroutinedemo.utils

import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class CustomBindingAdapter {
    companion object {

        @JvmStatic
        @BindingAdapter("bindAdapterSector")
        fun bindAdapterSector(recyclerView: RecyclerView, dataList: List<String>?) {
            Log.d("dataList-", "  " + dataList)

            dataList?.forEach {
                Log.d("dataList", "  " + it)
            }
        }

        @JvmStatic
        @BindingAdapter("progressListener")
        fun progressListener(
            progressBar: ProgressBar?,
            flag: Boolean?
        ) {
            progressBar?.isVisible= !flag!!
        }

        @JvmStatic
        @BindingAdapter("refreshListener")
        fun refreshListener(
            swipeRefresh: SwipeRefreshLayout?,
            refreshListener: SwipeRefreshLayout.OnRefreshListener?
        ) {
            swipeRefresh?.setOnRefreshListener(refreshListener)
        }

        @JvmStatic
        @BindingAdapter("setSwipeRefreshing")
        fun setSwipeRefreshing(swipeRefresh: SwipeRefreshLayout?, toRefresh: Boolean) {
            if (swipeRefresh != null) {
                swipeRefresh.isRefreshing = toRefresh
            }
        }

        @JvmStatic
        @BindingAdapter("setEnabledSwipeRefresh")
        fun setEnabledSwipeRefresh(swipeRefresh: SwipeRefreshLayout?, isEnabled: Boolean) {
            if (swipeRefresh != null) {
                swipeRefresh.isEnabled = isEnabled
            }
        }
    }
}