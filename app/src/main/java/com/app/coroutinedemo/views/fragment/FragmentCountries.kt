package com.app.coroutinedemo.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coroutinedemo.R
import com.app.coroutinedemo.businesslogic.interfaces.GeneralItemClickListeners
import com.app.coroutinedemo.businesslogic.pojo.countries.PojoCountriesItem
import com.app.coroutinedemo.businesslogic.viewmodel.fragment.ViewModelCountries
import com.app.coroutinedemo.databinding.FragmentCountriesBinding
import com.app.coroutinedemo.views.adapter.AdapterCountries

class FragmentCountries : FragmentBase() {


    private lateinit var mBinding: FragmentCountriesBinding
    private lateinit var mViewModel: ViewModelCountries
    private lateinit var mAdapter: AdapterCountries

    private var generalItemClickListeners = object : GeneralItemClickListeners {
        override fun onItemClick(view: View?, position: Int, item: Any?) {
            val model = item as PojoCountriesItem?

            val action =
                FragmentCountriesDirections.actionFragmentCountriesToFragmentEmission(model!!)

            mActivityMain?.navigateToEmission(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries, container, false)
        mViewModel = ViewModelProvider(mActivityMain!!)[ViewModelCountries::class.java]
        init()
        observe()
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.mViewModel = mViewModel
        mViewModel.fetchApi()

    }

    private fun init() {
        mAdapter = AdapterCountries(generalItemClickListeners)
        val mLinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mBinding.recCountries.adapter = mAdapter
        mBinding.recCountries.layoutManager = mLinearLayoutManager

    }

    private fun observe() {
        mViewModel.dataList.observe(viewLifecycleOwner, Observer {
            mAdapter.setList(it)

        })
        mViewModel.searchText.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                mAdapter.filter.filter(mViewModel.searchText.get())

            }
        })

    }
}