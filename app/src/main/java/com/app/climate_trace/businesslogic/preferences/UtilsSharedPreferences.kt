package com.ctuil.intranet.businesslogic.preferences

import android.content.Context
import android.content.SharedPreferences
import com.app.climate_trace.utils.Constants.PREF_NAME


class UtilsSharedPreferences {
    private var mPreferences: SharedPreferences
    private var mContext: Context

    constructor(context: Context) {
        mContext = context
        mPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    constructor(preferenceName: String?, context: Context) {
        mContext = context
        mPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }

    fun getBoolean(prefName: String?): Boolean {
        return mPreferences.getBoolean(prefName, false)
    }

    fun getBooleanDefault(prefName: String?, defaultValue: Boolean): Boolean {
        return mPreferences.getBoolean(prefName, defaultValue)
    }

    fun setBoolean(prefName: String?, value: Boolean) {
        mPreferences.edit().putBoolean(prefName, value).apply()
    }

    fun getString(prefName: String?): String? {
        return mPreferences.getString(prefName, "")
    }

    fun getStringDefault(prefName: String?, defaultValue: String?): String? {
        return mPreferences.getString(prefName, defaultValue)
    }

    fun setString(prefName: String?, value: String?) {
        mPreferences.edit().putString(prefName, value).apply()
    }

    fun getInteger(prefName: String?): Int {
        return mPreferences.getInt(prefName, 0)
    }

    fun getIntegerDefault(prefName: String?, defaultValue: Int): Int {
        return mPreferences.getInt(prefName, defaultValue)
    }

    fun setInteger(prefName: String?, value: Int) {
        mPreferences.edit().putInt(prefName, value).apply()
    }

    fun removePreference(prefName: String?) {
        mPreferences.edit().remove(prefName).apply()
    }

    fun getLong(prefName: String?): Long {
        return mPreferences.getLong(prefName, 0L)
    }

    fun getLongDefault(prefName: String?, defaultValue: Long): Long {
        return mPreferences.getLong(prefName, defaultValue)
    }

    fun setLong(prefName: String?, value: Long) {
        mPreferences.edit().putLong(prefName, value).apply()
    }

    fun clearAllPreferences() {
        mPreferences.edit().clear().apply()
    }
}