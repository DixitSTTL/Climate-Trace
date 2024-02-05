package com.app.coroutinedemo.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.coroutinedemo.R
import com.app.coroutinedemo.businesslogic.interfaces.GeneralClickListeners
import com.app.coroutinedemo.businesslogic.viewmodel.fragment.ViewModelDashboard
import com.app.coroutinedemo.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDashboard : FragmentBase() {

    private lateinit var mBinding: FragmentDashboardBinding
    private lateinit var mViewModel: ViewModelDashboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    val generalClickListeners = object : GeneralClickListeners {
        override fun onClick(view: View) {
            view.let {
                when (view.id) {

                    R.id.btnContinents -> {
                        mActivityMain!!.navigateToContinents()

                    }

                    R.id.btnCountries -> {
                        mActivityMain!!.navigateToCountries()

                    }

                    R.id.btnSectors -> {
                        mActivityMain!!.navigateToSectors()

                    }

                    R.id.btnSubSectors -> {
                        mActivityMain!!.navigateToSubSectors()

                    }

                    R.id.btnGases -> {
                        mActivityMain!!.navigateToGases()

                    }

                }

            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_dashboard, container, false)
        mViewModel = ViewModelProvider(this)[ViewModelDashboard::class.java]
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.generalClick = generalClickListeners
    }


}