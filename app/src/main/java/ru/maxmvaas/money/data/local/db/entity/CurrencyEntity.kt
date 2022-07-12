package ru.maxmvaas.money.data.local.db.entity

import android.os.Parcelable

import androidx.room.Entity

import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = ["date", "name"])
data class CurrencyEntity(
    var date: String,
    val name: String,
    val fullName: String,
    val rate: Double,
) : Parcelable