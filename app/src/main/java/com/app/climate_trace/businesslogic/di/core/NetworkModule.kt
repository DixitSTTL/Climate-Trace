package com.app.climate_trace.businesslogic.di.core

import android.content.Context
import com.app.climate_trace.MyApplication
import com.app.climate_trace.businesslogic.network.ApiCallFactory
import com.app.climate_trace.businesslogic.network.ApiHelper
import com.app.climate_trace.utils.StaticData
import com.app.paper.businesslogic.coroutine.AppDispatchersProvider
import com.app.paper.businesslogic.coroutine.DispatchersProvider
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideApplication(context: Context): MyApplication {
        return context as MyApplication
    }

    @Provides
    @Singleton
    fun providesRetrofitInterface(): ApiHelper {
        return ApiCallFactory.create(StaticData.getBaseURL())
    }

    @Provides
    fun providesDispatchers(): DispatchersProvider {
        return AppDispatchersProvider()
    }

    @Provides
    @Singleton
    fun providesPreferences(context: Context?): UtilsSharedPreferences {
        return UtilsSharedPreferences(context!!)
    }
}