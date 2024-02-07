package com.app.coroutinedemo.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.coroutinedemo.R
import com.app.coroutinedemo.businesslogic.interfaces.GeneralItemClickListeners
import com.app.coroutinedemo.businesslogic.viewmodel.fragment.ViewModelSubSectors
import com.app.coroutinedemo.databinding.FragmentSubSectorsBinding
import com.app.coroutinedemo.views.adapter.AdapterCommon
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSubSectors : FragmentBase() {

    private lateinit var mBinding: FragmentSubSectorsBinding
    private lateinit var mViewModel: ViewModelSubSectors
    private lateinit var mAdapter: AdapterCommon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sub_sectors, container, false)
        mViewModel = ViewModelProvider(mActivityMain!!)[ViewModelSubSectors::class.java]
        init()
        observe()

        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.mViewModel = mViewModel
        mBinding.touchListener = itemTouchHelper
        mViewModel.fetchApi()

    }

    private var generalItemClickListeners = object : GeneralItemClickListeners {
        override fun onItemClick(view: View?, position: Int, item: Any?) {
            if (view != null) {
                if (view.id == R.id.imageButton) {

                    mViewModel.deleteItem(item as String)
                }
            }
        }
    }

    private var itemTouchHelper =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val deletedCourse: String? = mViewModel.dataList.value?.get(viewHolder.adapterPosition)

                val position = viewHolder.adapterPosition

                deletedCourse?.let { mViewModel.deleteItem(it) }

                Snackbar.make(mBinding.recSubSectors, "Deleted ", Snackbar.LENGTH_LONG)
                    .setAction(
                        "Undo",
                        View.OnClickListener {
                            mViewModel.addItem(position, deletedCourse)

                        }).show()
            }

        }

    private fun init() {
        mAdapter = AdapterCommon(generalItemClickListeners)
        val mLinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mBinding.recSubSectors.adapter = mAdapter
        mBinding.recSubSectors.layoutManager = mLinearLayoutManager
    }

    private fun observe() {
        mViewModel.dataList.observe(viewLifecycleOwner, Observer {
            mAdapter.setList(it)
        })

    }

}