package com.demo.seed.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.demo.seed.data.CurrencyDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val currencyDao: CurrencyDao
) : ViewModel() {

    val currencyList = currencyDao.getCurrencyInfoList().asLiveData()
}