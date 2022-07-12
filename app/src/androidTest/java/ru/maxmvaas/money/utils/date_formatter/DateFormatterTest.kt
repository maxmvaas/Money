package ru.maxmvaas.money.utils.date_formatter

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.util.*

class DateFormatterTest {

    private var dateToTest: Date? = null

    @Before
    fun setUp() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2002)
            set(Calendar.MONTH, 0)
            set(Calendar.DAY_OF_MONTH, 26)
        }
        dateToTest = calendar.time
    }

    @Test
    fun dateNotNull() {
        assertNotNull(dateToTest)
    }

    @Test
    fun formatToParse() {
        assertEquals("2002/01/26", DateFormatter.formatToParse(dateToTest!!))
    }

    @Test
    fun formatToDefault() {
        assertEquals("26 января 2002.", DateFormatter.formatToDefault(dateToTest!!))
    }
}

