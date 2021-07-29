package com.demo.seed.ui

import androidx.lifecycle.*
import com.demo.seed.data.CurrencyDao
import com.demo.seed.data.CurrencyInfoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoActivityViewModel @Inject constructor(private val currencyDao: CurrencyDao) :
    ViewModel() {

    private var mutableCurrencyList = MutableLiveData<List<CurrencyInfoEntity>>(listOf())
    val currencyList: LiveData<List<CurrencyInfoEntity>> = mutableCurrencyList

    private var mutableCurrencyInfoSelection = MutableLiveData<CurrencyInfoEntity>()
    val currencyInfoSelected: LiveData<CurrencyInfoEntity> = mutableCurrencyInfoSelection

    fun updateSelectedCurrencyInfo(newValue: CurrencyInfoEntity) {
        mutableCurrencyInfoSelection.value = newValue
    }

    fun loadCurrencyList() {
        viewModelScope.launch(Dispatchers.IO) {
            currencyDao.getCurrencyInfoList().collect {
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