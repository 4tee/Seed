package com.demo.seed.data

import com.demo.seed.coroutines.DefaultDispatcherProvider
import com.demo.seed.coroutines.DispatcherProvider
import com.demo.seed.ui.model.CurrencyInfo
import kotlinx.coroutines.withContext

class SortRepository(private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()) {

    /**
     * For heavy computation, Dispatcher.DEFAULT is a good option since It is backed by
     * a shared pool of threads on JVM. By default, the maximum number of threads used
     * by this dispatcher is equal to the number of CPU cores, but is at least two.
     */
    suspend fun sortInMemory(rawList: List<CurrencyInfo>): List<CurrencyInfo> {
        return withContext(dispatchers.default()) {
            rawList.sortedBy { it.symbol }
        }
    }
}