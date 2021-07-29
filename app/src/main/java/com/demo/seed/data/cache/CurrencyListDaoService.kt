package com.demo.seed.data.cache

import com.demo.seed.data.model.CurrencyInfoEntity

interface CurrencyListDaoService {

    suspend fun get(): List<CurrencyInfoEntity>
}