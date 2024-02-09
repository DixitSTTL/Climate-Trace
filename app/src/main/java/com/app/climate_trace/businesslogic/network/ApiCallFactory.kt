package com.app.climate_trace.businesslogic.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiCallFactory {

    private const val RETROFIT_TIMEOUT: Long = 60
    fun create(url: String): ApiHelper {
        return getInstance(url).create(ApiHelper::class.java)

    }


    private fun getInstance(url: String): Retrofit {
        var loggingInterceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
        builder.readTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
        builder.writeTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
        builder.addInterceptor(loggingInterceptor)


        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()

    }

}