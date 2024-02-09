package com.app.climate_trace.businesslogic.pojo.emission

data class CountryData(
    val AssetCount: Int,
    val Country: String,
    val Emissions: Double,
    val Gas: String,
    val Sector: String,
    val Year: Int
)