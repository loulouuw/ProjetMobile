package com.example.projetmobile.model

import com.google.gson.annotations.SerializedName

data class Pays(
    @SerializedName("name")
    val nom: String,
    @SerializedName("flag")
    val drapeau: String
)
