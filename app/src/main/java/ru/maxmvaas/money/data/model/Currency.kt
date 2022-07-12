package ru.maxmvaas.money.data.model

import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    @SerializedName("CharCode")
    val name: String,
    @SerializedName("Name")
    val fullName: String,
    @SerializedName("Value")
    val rate: Double
) : Parcelable
