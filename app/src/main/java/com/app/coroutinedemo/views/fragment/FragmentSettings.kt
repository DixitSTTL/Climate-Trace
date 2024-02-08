package com.app.coroutinedemo.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import com.app.coroutinedemo.R
import com.app.coroutinedemo.businesslogic.viewmodel.fragment.ViewModelSettings
import com.app.coroutinedemo.databinding.FragmentSettingsBinding
import com.app.coroutinedemo.utils.Constants.SettingsConstantCodes.IS_DARK_MODE_ENABLED
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSettings : FragmentBase() {
    private lateinit var mBinding: FragmentSettingsBinding
    private lateinit var mViewModel: ViewModelSettings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        mViewModel = ViewModelProvider(mActivityMain!!)[ViewModelSettings::class.java]
        init()
        observe()
        return mBinding.root
    }

    private fun init() {
        mViewModel.isDarkModeEnable.set(
            mPreferences.getBooleanDefault(
                IS_DARK_MODE_ENABLED,
                mApplication.isSystemInDarkMode(requireContext())
            )
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.mViewModel = mViewModel
    }

    private fun observe() {

        mViewModel.isDarkModeEnable.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

                mPreferences.setBoolean(IS_DARK_MODE_ENABLED, mViewModel.isDarkModeEnable.get())
                mApplication.init()

            }

        })
    }

}