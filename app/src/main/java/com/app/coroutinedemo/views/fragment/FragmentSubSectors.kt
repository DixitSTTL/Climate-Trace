package com.app.coroutinedemo.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coroutinedemo.R
import com.app.coroutinedemo.businesslogic.interfaces.GeneralItemClickListeners
import com.app.coroutinedemo.businesslogic.viewmodel.fragment.ViewModelSubSectors
import com.app.coroutinedemo.databinding.FragmentSubSectorsBinding
import com.app.coroutinedemo.views.adapter.AdapterCommon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSubSectors : FragmentBase() {

    private lateinit var mBinding: FragmentSubSectorsBinding
    private lateinit var mViewModel: ViewModelSubSectors
    private lateinit var mAdapter: AdapterCommon
    private var generalItemClickListeners = object : GeneralItemClickListeners {
        override fun onItemClick(view: View?, position: Int, item: Any?) {
            if (view != null) {
                if (view.id == R.id.imageButton) {

                    mViewModel.deleteItem(item as String)
                }
            }
        }
    }
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
        mViewModel.fetchApi()

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