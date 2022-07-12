package ru.maxmvaas.money.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

import ru.maxmvaas.money.data.local.db.dao.CurrencyDao
import ru.maxmvaas.money.data.local.db.entity.CurrencyEntity
import ru.maxmvaas.money.data.model.Currency
import ru.maxmvaas.money.utils.model_formatter.ModelFormatter

class CurrencyLocalRepository(private val currencyDao: CurrencyDao) {
    suspend fun update(currencies: List<Currency>, date: String) {
        withContext(Dispatchers.IO) {
            currencies.forEach {
                val currencyEntity = ModelFormatter.convertToEntity(it)
                currencyEntity.date = date
                currencyDao.insert(currencyEntity)
            }
        }
    }

    suspend fun isRowIsExist(date: String): Boolean {
        var result: Boolean
        withContext(Dispatchers.IO) {
            result = currencyDao.isRowIsExist(date)
        }
        return result
    }

    fun getData(date: String): Flow<List<CurrencyEntity>> = currencyDao.getAll(date)
}