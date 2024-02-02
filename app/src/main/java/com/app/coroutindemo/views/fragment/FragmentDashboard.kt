package com.app.coroutindemo.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.coroutindemo.R
import com.app.coroutindemo.businesslogic.interfaces.GeneralClickListeners
import com.app.coroutindemo.businesslogic.viewmodel.fragment.ViewmodelDashboard
import com.app.coroutindemo.databinding.FragmentDashboardBinding
import com.app.coroutindemo.views.activity.ActivityMain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDashboard : FragmentBase() {

    private lateinit var mBinding: FragmentDashboardBinding
    private lateinit var mViewModel: ViewmodelDashboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    val generalClickListeners = object :GeneralClickListeners{
        override fun onClick(view: View) {
            view.let {
                when(view.id){

                    R.id.btnContinents->{
                        mActivityMain!!.navigateToContinents()

                    }
                    R.id.btnCountries->{
                        mActivityMain!!.navigateToCountries()

                    }
                    R.id.btnSectors->{
                        mActivityMain!!.navigateToSectors()

                    }

                }

            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_dashboard,container,false)
        mViewModel = ViewModelProvider(this)[ViewmodelDashboard::class.java]
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.generalClick=generalClickListeners
    }


}