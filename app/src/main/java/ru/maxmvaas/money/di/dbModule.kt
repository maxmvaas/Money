package ru.maxmvaas.money.di

import androidx.room.Room

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

import ru.maxmvaas.money.data.local.CurrencyLocalRepository
import ru.maxmvaas.money.data.local.db.CurrencyDatabase

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            CurrencyDatabase::class.java,
            "currency_database"
        ).fallbackToDestructiveMigration().build()
    }

    single {
        get<CurrencyDatabase>().getCurrencyDao()
    }

    single {
        CurrencyLocalRepository(get())
    }
}