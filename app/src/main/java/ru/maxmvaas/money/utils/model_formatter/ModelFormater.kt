package ru.maxmvaas.money.utils.model_formatter

import ru.maxmvaas.money.data.local.db.entity.CurrencyEntity
import ru.maxmvaas.money.data.model.Currency

object ModelFormatter {
    fun convertToEntity(currency: Currency) = CurrencyEntity(
        date = "",
        name = currency.name,
        fullName = currency.fullName,
        rate = currency.rate
    )

    fun convertToDefault(currencyEntity: CurrencyEntity) = Currency(
        name = currencyEntity.name,
        fullName = currencyEntity.fullName,
        rate = currencyEntity.rate
    )
}