package com.example.animals

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    val name: String,
    val description: String,
    val photo: Int,
    val habitat: String,
    val masaHidup: String
) : Parcelable
