package com.app.climate_trace.businesslogic.pojo.countries

import android.os.Parcel
import android.os.Parcelable

data class PojoCountriesItem(
    val alpha2: String,
    val alpha3: String,
    val continent: String,
    val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(alpha2)
        parcel.writeString(alpha3)
        parcel.writeString(continent)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PojoCountriesItem> {
        override fun createFromParcel(parcel: Parcel): PojoCountriesItem {
            return PojoCountriesItem(parcel)
        }

        override fun newArray(size: Int): Array<PojoCountriesItem?> {
            return arrayOfNulls(size)
        }
    }
}