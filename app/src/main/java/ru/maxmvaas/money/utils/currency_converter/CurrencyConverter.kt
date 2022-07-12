package ru.maxmvaas.money.utils.currency_converter

object CurrencyConverter {
    fun convertToRubles(value: String, rate: Double): String {
        value.replace(',', '.')
        val result = if (value.length == 1) {
            "%.1f".format(value.toDouble() * rate)
        } else {
            "%.2f".format(value.toDouble() * rate)
        }
        return prepareToOutput(result)
    }

    fun convertFromRubles(value: String, rate: Double): String {
        value.replace(',', '.')
        val result = if (value.length == 1) {
            "%.1f".format(value.toDouble() / rate)
        } else {
            "%.2f".format(value.toDouble() / rate)
        }
        return prepareToOutput(result)
    }

    fun prepareToOutput(input: String): String {
        var result = input
        while (result.last() == '0' || result.last() == '.') {
            result = result.dropLast(1)
        }
        if (result.last() == '.' || result.last() == ',') {
            result = result.dropLast(1)
        }
        return result.replace(',', '.')
    }
}