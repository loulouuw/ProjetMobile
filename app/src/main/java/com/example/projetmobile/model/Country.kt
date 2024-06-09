package com.example.projetmobile.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val name: Name,
    @SerializedName("capital")
    val capital: List<String>?,
    @SerializedName("flags")
    val flags: Flags
)

data class Name(
    @SerializedName("common")
    val common: String
)

data class Flags(
    @SerializedName("png")
    val png: String
)
