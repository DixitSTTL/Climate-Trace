package com.app.climate_trace.utils

object StaticData {

    init {
        System.loadLibrary("securedata-lib")
    }

    external fun getBaseURL(): String


}