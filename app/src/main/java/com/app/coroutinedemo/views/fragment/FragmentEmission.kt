package com.app.coroutinedemo.views.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coroutinedemo.R
import com.app.coroutinedemo.businesslogic.viewmodel.fragment.ViewmodelEmission
import com.app.coroutinedemo.databinding.FragmentEmissionBinding
import com.app.coroutinedemo.views.adapter.AdapterEmission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentEmission : FragmentBase() {

    private lateinit var mBinding: FragmentEmissionBinding
    private lateinit var mViewModel: ViewmodelEmission
    private var mAdapter: AdapterEmission = AdapterEmission()
    private val args: FragmentEmissionArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_emission, container, false)
        mViewModel = ViewModelProvider(mActivityMain!!)[ViewmodelEmission::class.java]
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

        val mLinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mBinding.recSubSectors.adapter = mAdapter
        mBinding.recSubSectors.layoutManager = mLinearLayoutManager
        mViewModel.dataCountry.set(args.countryItem)
    }

    private fun observe() {
        mViewModel.dataList.observe(viewLifecycleOwner, Observer {


            mAdapter.setList(it)
        })

    }

}