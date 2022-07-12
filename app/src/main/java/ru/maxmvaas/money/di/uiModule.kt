package ru.maxmvaas.money.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

import ru.maxmvaas.money.presentation.convertation.ConvertationViewModel
import ru.maxmvaas.money.presentation.currencies_list.CurrenciesListViewModel

val uiModule = module {
    viewModel {
        CurrenciesListViewModel(get(), get())
    }

    viewModel {
        ConvertationViewModel()
    }
}