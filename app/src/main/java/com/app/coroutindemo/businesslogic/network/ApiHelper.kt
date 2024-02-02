package com.app.coroutindemo.businesslogic.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiHelper {

    @GET(NetworkController.API_CONTINENTS)
    suspend fun fetchContinents(): Response<List<String>>

    @GET(NetworkController.API_COUNTRIES)
    suspend fun fetchCountries(): Response<List<String>>

    @GET(NetworkController.API_SECTORS)
    suspend fun fetchSectors(): Response<List<String>>

    @GET(NetworkController.API_SUBSECTORS)
    suspend fun fetchSubSectors(): Response<List<String>>

    @GET(NetworkController.API_GASES)
    suspend fun fetchGases(): Response<List<String>>

}