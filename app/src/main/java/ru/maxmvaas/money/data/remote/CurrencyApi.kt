package ru.maxmvaas.money.data.remote

import com.google.gson.annotations.SerializedName

import retrofit2.http.GET
import retrofit2.http.Path

import ru.maxmvaas.money.data.model.Currency

interface CurrencyApi {
    @GET("{date}/daily_json.js")
    suspend fun getCurrency(@Path("date") date: String): CurrencyList
}

data class CurrencyList(
    @SerializedName("Valute")
    val currencies: Map<String, Currency>
)