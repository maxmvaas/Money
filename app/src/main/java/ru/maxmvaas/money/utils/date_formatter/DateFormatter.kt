package ru.maxmvaas.money.utils.date_formatter

import android.text.format.DateFormat

import java.util.*

object DateFormatter {
    private const val FORMAT_TO_PARSE = "yyyy/MM/dd"
    private const val FORMAT_DEFAULT = "d MMMM yyyy."

    fun formatToParse(date: Date) = DateFormat.format(FORMAT_TO_PARSE, date).toString()
    
    fun formatToDefault(date: Date) = DateFormat.format(FORMAT_DEFAULT, date).toString()
}

