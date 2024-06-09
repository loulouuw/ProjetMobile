package com.example.projetmobile.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.projetmobile.model.Country


interface CountryApiService {
    @GET("v3.1/name/{name}")
    fun getCountryByName(@Path("name") name: String): Call<List<Country>>
}
