package com.example.projetmobile.api

import com.example.projetmobile.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApiService {
    interface CountryApiService {
        @GET("v3.1/all")
        fun getAllCountries(): Call<List<Country>>
    }
    @GET("v3.1/name/{name}")
    fun getCountriesByName(@Path("name") name: String): Call<List<Country>>

    @GET("v3.1/capital/{capital}")
    fun getCountriesByCapital(@Path("capital") capital: String): Call<List<Country>>
}
