package ru.maxmvaas.money.data.remote

import ru.maxmvaas.money.data.model.Currency

class CurrencyRemoteRepository(private val currencyApi: CurrencyApi) {
    private var currencies: List<Currency> = mutableListOf()

    private suspend fun loadCurrencies(date: String) {
        currencies = currencyApi.getCurrency(date).currencies.values.toList()
    }

    suspend fun getCurrencies(date: String): List<Currency> {
        if (currencies.isNotEmpty()) {
            currencies = mutableListOf()
            loadCurrencies(date)
        }
        loadCurrencies(date)
        return currencies
    }
}