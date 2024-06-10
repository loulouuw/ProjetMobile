package com.example.projetmobile.api

import com.example.projetmobile.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface CountryApiService {
    @GET("v3.1/all")
    fun getAllCountries(): Call<List<Country>>

    @GET("v3.1/name/{name}")
    fun getCountriesByName(@retrofit2.http.Path("name") name: String): Call<List<Country>>
}
