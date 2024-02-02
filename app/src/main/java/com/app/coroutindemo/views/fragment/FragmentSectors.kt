package com.app.coroutindemo.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ObservableList.OnListChangedCallback
import androidx.lifecycle.ViewModelProvider
import com.app.coroutindemo.R
import com.app.coroutindemo.businesslogic.viewmodel.fragment.ViewmodelSectors
import com.app.coroutindemo.databinding.FragmentSectorsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSectors : FragmentBase() {

    private lateinit var mBinding: FragmentSectorsBinding
    private lateinit var mViewModel: ViewmodelSectors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sectors, container, false)
        mViewModel = ViewModelProvider(mActivityMain!!)[ViewmodelSectors::class.java]


        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.mViewModel = mViewModel
        mViewModel.fetchApi()

    }

}