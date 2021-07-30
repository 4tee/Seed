package com.demo.seed.data

import com.demo.seed.MainCoroutineRule
import com.demo.seed.data.cache.CacheDataSource
import com.demo.seed.ui.model.CurrencyInfo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CurrencyRepositoryTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mockDataSource: CacheDataSource = mock()
    private val sample = listOf(
        CurrencyInfo("A", "A", "A"),
        CurrencyInfo("B", "B", "B"),
        CurrencyInfo("C", "C", "C")
    )

    @Test
    fun `fetching currency list from repository`() =
        mainCoroutineRule.testDispatcher.runBlockingTest {
            whenever(mockDataSource.get()).thenReturn(sample)
            val repository =
                CurrencyRepository(mockDataSource, mainCoroutineRule.testDispatcherProvider)

            assertThat(repository.execute()).isEqualTo(sample)
        }
}