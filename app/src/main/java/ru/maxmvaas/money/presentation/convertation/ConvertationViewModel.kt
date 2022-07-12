package ru.maxmvaas.money.presentation.convertation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import ru.maxmvaas.money.utils.currency_converter.CurrencyConverter

class ConvertationViewModel : ViewModel() {
    private val _valueLiveData = MutableLiveData<String>()
    val valueLiveData: LiveData<String> get() = _valueLiveData

    private val _valueRublesLiveData = MutableLiveData<String>()
    val valueRoublesLiveData: MutableLiveData<String> get() = _valueRublesLiveData

    fun convertToRubles(value: String, rate: Double) =
        _valueRublesLiveData.postValue(CurrencyConverter.convertToRubles(value, rate))

    fun convertFromRubles(value: String, rate: Double) =
        _valueLiveData.postValue(CurrencyConverter.convertFromRubles(value, rate))
}