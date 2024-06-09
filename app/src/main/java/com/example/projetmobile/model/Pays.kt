package com.example.projetmobile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pays(
    val name: String
) : Parcelable
