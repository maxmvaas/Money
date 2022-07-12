package ru.maxmvaas.money.utils.currency_converter

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CurrencyConverterTest {

    private var value: String? = null
    private var rate: Double? = null

    @Before
    fun setUp() {
        value = "13"
        rate = 12.9
    }

    @Test
    fun valueAndRateNotNull() {
        assertNotNull(value)
        assertNotNull(rate)
    }

    @Test
    fun convertToRublesTest() {
        assertEquals("167.7", CurrencyConverter.convertToRubles(value!!, rate!!))
        value = "1234567890"
        assertEquals("15925925781", CurrencyConverter.convertToRubles(value!!, rate!!))
    }

    @Test
    fun convertFromRublesTest() {
        value = "13"
        assertEquals("1.01", CurrencyConverter.convertFromRubles(value!!, rate!!))
        value = "1234567890"
        assertEquals("95702937.21", CurrencyConverter.convertFromRubles(value!!, rate!!))
    }

    @Test
    fun prepareToOutputTest() {
        value = "124.90"
        assertEquals("124.9", CurrencyConverter.prepareToOutput(value!!))
        value = "124,900"
        assertEquals("124.9", CurrencyConverter.prepareToOutput(value!!))
        value = "124,916"
        assertEquals("124.916", CurrencyConverter.prepareToOutput(value!!))
        value = "124,"
        assertEquals("124", CurrencyConverter.prepareToOutput(value!!))
        value = "124,000"
        assertEquals("124", CurrencyConverter.prepareToOutput(value!!))
    }
}

