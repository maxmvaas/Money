package ru.maxmvaas.money.presentation.currencies_list

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

import ru.maxmvaas.money.data.local.CurrencyLocalRepository
import ru.maxmvaas.money.data.model.Currency
import ru.maxmvaas.money.data.remote.CurrencyRemoteRepository
import ru.maxmvaas.money.utils.CurrencyState
import ru.maxmvaas.money.utils.model_formatter.ModelFormatter

class CurrenciesListViewModel(
    private val currencyRemoteRepository: CurrencyRemoteRepository,
    private val currencyLocalRepository: CurrencyLocalRepository
) :
    ViewModel() {
    private val _currencyListStateLiveData = MutableLiveData<CurrencyState>()
    val currencyListStateLiveData: LiveData<CurrencyState> = _currencyListStateLiveData

    fun loadCurrencies(date: String) {
        _currencyListStateLiveData.postValue(CurrencyState.Loading)
        viewModelScope.launch {
            val isRowExist = currencyLocalRepository.isRowIsExist(date)
            if (isRowExist) {
                Log.d(TAG, "Row with date $date exists, loading from database...")
                getCurrenciesLocal(date)
            } else {
                Log.d(TAG, "Row with date $date doesn't exists, loading from API...")
                getCurrenciesNet(date)
            }
        }
    }

    private suspend fun getCurrenciesNet(date: String) {
        viewModelScope.launch {
            try {
                val currencies = currencyRemoteRepository.getCurrencies(date)
                _currencyListStateLiveData.postValue(CurrencyState.Data(currencies))
                Log.d(TAG, "Data for $date successfully loaded. Adding row in database...")
                currencyLocalRepository.update(currencies, date)
                Log.d(TAG, "Data for $date successfully added to database.")
            } catch (exception: Exception) {
                Log.d(TAG, "An error occurred: $exception")
                _currencyListStateLiveData.postValue(CurrencyState.Error)
            }
        }
    }

    private suspend fun getCurrenciesLocal(date: String) {
        val currencies = mutableListOf<Currency>()
        viewModelScope.launch {
            try {
                currencyLocalRepository.getData(date).first().forEach { currencyEntity ->
                    currencies.add(ModelFormatter.convertToDefault(currencyEntity))
                }
                Log.d(TAG, "Data for $date successfully loaded.")
                _currencyListStateLiveData.postValue(CurrencyState.Data(currencies))
            } catch (exception: Exception) {
                Log.d(TAG, "An error occurred: $exception")
                _currencyListStateLiveData.postValue(CurrencyState.Error)
            }
        }
    }

    companion object {
        private const val TAG = "[CurrenciesListViewModel]"
    }
}