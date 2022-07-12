package ru.maxmvaas.money.utils.convertation_input_filter

import android.text.InputFilter
import android.text.Spanned

import java.util.regex.Pattern

class DecimalDigitsInputFilter(
    private val digitsBeforeZero: Int,
    private val digitsAfterZero: Int
) :
    InputFilter {
    private var mPattern: Pattern? = null
    private fun applyPattern(digitsBeforeZero: Int, digitsAfterZero: Int) {
        Pattern.compile("[0-9]{0,${digitsBeforeZero - 1}}+((\\.[0-9]{0,${digitsAfterZero - 1}})?)|(\\.)?")
            .also { mPattern = it }
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        if (dest.toString().contains(".") || source.toString().contains(".")) applyPattern(
            digitsBeforeZero + 2,
            digitsAfterZero
        ) else applyPattern(digitsBeforeZero, digitsAfterZero)
        val matcher = mPattern!!.matcher(dest)
        return if (!matcher.matches()) "" else null
    }

    init {
        applyPattern(digitsBeforeZero, digitsAfterZero)
    }
}