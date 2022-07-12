package ru.maxmvaas.money.di

import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import ru.maxmvaas.money.data.remote.CurrencyApi
import ru.maxmvaas.money.data.remote.CurrencyRemoteRepository

private const val URL = "https://www.cbr-xml-daily.ru/archive/"

val netModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CurrencyApi::class.java)
    }

    single {
        CurrencyRemoteRepository(get())
    }
}