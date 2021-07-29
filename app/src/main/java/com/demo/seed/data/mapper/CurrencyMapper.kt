package com.demo.seed.data.mapper

import com.demo.seed.data.model.CurrencyInfoEntity
import com.demo.seed.ui.model.CurrencyInfo
import com.demo.seed.util.EntityMapper
import javax.inject.Inject

class CurrencyMapper
@Inject
constructor() : EntityMapper<CurrencyInfoEntity, CurrencyInfo> {

    override fun mapFromEntity(entity: CurrencyInfoEntity): CurrencyInfo {
        return CurrencyInfo(id = entity.id, name = entity.name, symbol = entity.symbol)
    }

    override fun mapToEntity(domainModel: CurrencyInfo): CurrencyInfoEntity {
        return CurrencyInfoEntity(
            id = domainModel.id,
            name = domainModel.name,
            symbol = domainModel.symbol
        )
    }

    fun mapFromEntityList(entities: List<CurrencyInfoEntity>): List<CurrencyInfo> {
        return entities.map { mapFromEntity(it) }
    }
}