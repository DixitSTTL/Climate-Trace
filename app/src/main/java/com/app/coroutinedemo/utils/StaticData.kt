package com.app.coroutinedemo.utils

object StaticData {

    init {
        System.loadLibrary("securedata-lib")
    }

    external fun getBaseURL(): String


}