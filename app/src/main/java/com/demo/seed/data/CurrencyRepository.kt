package com.demo.seed.data

import com.demo.seed.data.cache.CacheDataSource
import com.demo.seed.ui.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val cacheDataSource: CacheDataSource) {

    suspend fun execute(): Flow<List<CurrencyInfo>> = flow {
        emit(cacheDataSource.get())
    }
}