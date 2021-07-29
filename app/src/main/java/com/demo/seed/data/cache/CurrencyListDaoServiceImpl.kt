package com.demo.seed.data.cache

import com.demo.seed.data.model.CurrencyInfoEntity
import com.demo.seed.data.room.CurrencyDao

class CurrencyListDaoServiceImpl(private val currencyDao: CurrencyDao) : CurrencyListDaoService {

    override suspend fun get(): List<CurrencyInfoEntity> {
        return currencyDao.getCurrencyInfoList()
    }
}