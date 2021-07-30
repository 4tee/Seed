package com.demo.seed.data.cache

import com.demo.seed.data.mapper.CurrencyMapper
import com.demo.seed.ui.model.CurrencyInfo

class CacheDataSourceImpl(
    private val currencyListDaoService: CurrencyListDaoService,
    private val currencyMapper: CurrencyMapper,
) : CacheDataSource {

    override suspend fun get(): List<CurrencyInfo> {
        return currencyListDaoService.get().map { currencyMapper.mapFromEntity(it) }
    }
}