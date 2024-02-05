package com.app.coroutinedemo.businesslogic.di

import android.content.Context
import com.app.coroutinedemo.MyApplication
import com.app.coroutinedemo.businesslogic.network.ApiCallFactory
import com.app.coroutinedemo.businesslogic.network.ApiHelper
import com.app.paper.businesslogic.coroutine.AppDispatchersProvider
import com.app.paper.businesslogic.coroutine.DispatchersProvider
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
        return ApiCallFactory.create("https://api.climatetrace.org/v4/")
    }

    @Provides
    fun providesDispatchers(): DispatchersProvider {
        return AppDispatchersProvider()
    }


}