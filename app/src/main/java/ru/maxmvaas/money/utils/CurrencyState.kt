package ru.maxmvaas.money.utils

import ru.maxmvaas.money.data.model.Currency

sealed class CurrencyState {
    object Loading : CurrencyState()
    class Data(val data: List<Currency>) : CurrencyState()
    object Error : CurrencyState()
}