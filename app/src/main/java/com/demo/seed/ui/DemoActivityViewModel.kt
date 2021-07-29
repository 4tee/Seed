package com.demo.seed.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.seed.data.CurrencyRepository
import com.demo.seed.ui.model.CurrencyInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoActivityViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) :
    ViewModel() {

    private var mutableCurrencyList = MutableLiveData<List<CurrencyInfo>>(listOf())
    val currencyList: LiveData<List<CurrencyInfo>> = mutableCurrencyList

    private var mutableCurrencyInfoSelection = MutableLiveData<CurrencyInfo>()
    val currencyInfoSelected: LiveData<CurrencyInfo> = mutableCurrencyInfoSelection

    fun updateSelectedCurrencyInfo(newValue: CurrencyInfo) {
        mutableCurrencyInfoSelection.value = newValue
    }

    fun loadCurrencyList() {
        viewModelScope.launch(Dispatchers.IO) {
            currencyRepository.execute().collect {
                mutableCurrencyList.postValue(it)
            }
        }
    }

    fun sortCurrencyList() {
        viewModelScope.launch(Dispatchers.Default) {
            currencyList.value?.let { list ->
                val sortedList = list.sortedBy { it.symbol }
                mutableCurrencyList.postValue(sortedList)
            }
        }
    }
}