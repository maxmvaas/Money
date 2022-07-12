package ru.maxmvaas.money.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

import ru.maxmvaas.money.data.local.db.dao.CurrencyDao
import ru.maxmvaas.money.data.local.db.entity.CurrencyEntity

@Database(
    entities = [CurrencyEntity::class],
    version = 1
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao
}