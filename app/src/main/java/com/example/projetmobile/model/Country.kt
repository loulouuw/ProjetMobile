package com.example.projetmobile.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val name: Name,
    @SerializedName("flags")
    val flags: Flags,
    @SerializedName("capital")
    val capital: List<String>?,
    @SerializedName("region")
    val region: String,
    @SerializedName("subregion")
    val subregion: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("borders")
    val borders: List<String>?
)

data class Name(
    @SerializedName("common")
    val common: String
)

data class Flags(
    @SerializedName("png")
    val png: String
)
