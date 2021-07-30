package com.demo.seed.data

import com.demo.seed.coroutines.DefaultDispatcherProvider
import com.demo.seed.coroutines.DispatcherProvider
import com.demo.seed.data.cache.CacheDataSource
import com.demo.seed.ui.model.CurrencyInfo
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val cacheDataSource: CacheDataSource,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) {

    suspend fun execute(): List<CurrencyInfo> {
        return withContext(dispatchers.io()) {
            // since we only have one data source, we will just return from cache
            // if there is remote data source, we can add logic here.
            cacheDataSource.get()
        }
    }
}