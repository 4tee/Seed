package com.demo.seed.data.cache

import com.demo.seed.ui.model.CurrencyInfo

interface CacheDataSource {
    suspend fun get(): List<CurrencyInfo>
}