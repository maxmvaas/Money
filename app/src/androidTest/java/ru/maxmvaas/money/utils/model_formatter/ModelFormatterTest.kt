package ru.maxmvaas.money.utils.model_formatter

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.maxmvaas.money.data.local.db.entity.CurrencyEntity
import ru.maxmvaas.money.data.model.Currency

class ModelFormatterTest {

    private val currency = Currency("USD", "Американский доллар", 55.6)

    private val currencyEntity = CurrencyEntity("26.01.2002", "USD", "Американский доллар", 55.6)

    @Test
    fun convertToEntity() {
        val convertedCurrencyEntity = ModelFormatter.convertToEntity(currency)
        assertEquals(currency.rate, convertedCurrencyEntity.rate, 0.001)
        assertEquals(currency.name, convertedCurrencyEntity.name)
        assertEquals(currency.fullName, convertedCurrencyEntity.fullName)
    }

    @Test
    fun convertToDefault() {
        val convertedCurrency = ModelFormatter.convertToDefault(currencyEntity)
        assertEquals(currency.rate, convertedCurrency.rate, 0.001)
        assertEquals(currency.name, convertedCurrency.name)
        assertEquals(currency.fullName, convertedCurrency.fullName)
    }

}