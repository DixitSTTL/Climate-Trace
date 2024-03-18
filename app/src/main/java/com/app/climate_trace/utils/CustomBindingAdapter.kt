package com.app.climate_trace.utils

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class CustomBindingAdapter {
    companion object {

        @JvmStatic
        @BindingAdapter("progressListener")
        fun progressListener(
            progressBar: ProgressBar?,
            flag: Boolean?
        ) {
            progressBar?.isVisible = !flag!!
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

        @JvmStatic
        @BindingAdapter("setSwipeToDelete")
        fun setSwipeToDelete(
            recyclerView: RecyclerView?,
            touchListener: ItemTouchHelper.SimpleCallback
        ) {
            if (recyclerView != null) {
                ItemTouchHelper(touchListener).attachToRecyclerView(recyclerView)
            }
        }
    }
}