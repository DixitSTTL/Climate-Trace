package com.app.climate_trace.businesslogic.network

import com.app.climate_trace.businesslogic.pojo.countries.PojoCountries
import com.app.climate_trace.businesslogic.pojo.emission.CountryData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {

    @GET(NetworkController.API_CONTINENTS)
    suspend fun fetchContinents(): Response<ArrayList<String>>?

    @GET(NetworkController.API_COUNTRIES)
    suspend fun fetchCountries(): Response<PojoCountries>?

    @GET(NetworkController.API_SECTORS)
    suspend fun fetchSectors(): Response<ArrayList<String>>?

    @GET(NetworkController.API_SUBSECTORS)
    suspend fun fetchSubSectors(): Response<ArrayList<String>>?

    @GET(NetworkController.API_GASES)
    suspend fun fetchGases(): Response<ArrayList<String>>?

    @GET(NetworkController.API_EMISSION)
    suspend fun fetchEmissionOfCountry(
        @Query(value = "countries")
        countries: String
    ): Response<HashMap<String, List<CountryData>>>?

}