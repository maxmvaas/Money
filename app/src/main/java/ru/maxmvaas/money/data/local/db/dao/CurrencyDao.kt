package ru.maxmvaas.money.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import kotlinx.coroutines.flow.Flow

import ru.maxmvaas.money.data.local.db.entity.CurrencyEntity

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyEntity: CurrencyEntity)

    @Query("SELECT * FROM CurrencyEntity WHERE date = :date")
    fun getAll(date: String): Flow<List<CurrencyEntity>>

    @Query("SELECT EXISTS(SELECT * FROM CurrencyEntity WHERE date = :date)")
    fun isRowIsExist(date: String): Boolean

    @Query("DELETE FROM CurrencyEntity")
    fun clearTable(): Int
}