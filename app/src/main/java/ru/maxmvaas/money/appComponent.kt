package ru.maxmvaas.money

import ru.maxmvaas.money.di.dbModule
import ru.maxmvaas.money.di.netModule
import ru.maxmvaas.money.di.uiModule

val appComponent = listOf(uiModule, netModule, dbModule)
