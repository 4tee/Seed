package com.demo.seed.data.mapper

import com.demo.seed.data.model.CurrencyInfoEntity
import com.demo.seed.ui.model.CurrencyInfo
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CurrencyMapperTest {

    private val currencyMapper = CurrencyMapper()

    @Test
    fun `GIVEN entity, when mapFromEntity is passed, object will be generated`() {
        val entity = CurrencyInfoEntity("id", "name", "symbol")
        assertThat(currencyMapper.mapFromEntity(entity)).isEqualTo(
            CurrencyInfo(
                "id",
                "name",
                "symbol"
            )
        )
    }

    @Test
    fun `GIVEN domainModel, when mapToEntity is passed, entity will be generated`() {
        val domainModel = CurrencyInfo("id", "name", "symbol")
        assertThat(currencyMapper.mapToEntity(domainModel)).isEqualTo(
            CurrencyInfoEntity(
                "id",
                "name",
                "symbol"
            )
        )
    }
}