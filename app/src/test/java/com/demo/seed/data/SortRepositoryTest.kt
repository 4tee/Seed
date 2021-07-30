package com.demo.seed.data

import com.demo.seed.MainCoroutineRule
import com.demo.seed.ui.model.CurrencyInfo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class SortRepositoryTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val sampleRaw = listOf(
        CurrencyInfo("A", "A", "A"),
        CurrencyInfo("G", "G", "G"),
        CurrencyInfo("C", "C", "C"),
        CurrencyInfo("I", "I", "I"),
        CurrencyInfo("F", "F", "F"),
        CurrencyInfo("D", "D", "D"),
        CurrencyInfo("E", "E", "E"),
        CurrencyInfo("H", "H", "H"),
        CurrencyInfo("B", "B", "B")
    )

    private val sampleSorted = listOf(
        CurrencyInfo("A", "A", "A"),
        CurrencyInfo("B", "B", "B"),
        CurrencyInfo("C", "C", "C"),
        CurrencyInfo("D", "D", "D"),
        CurrencyInfo("E", "E", "E"),
        CurrencyInfo("F", "F", "F"),
        CurrencyInfo("G", "G", "G"),
        CurrencyInfo("H", "H", "H"),
        CurrencyInfo("I", "I", "I")
    )

    @Test
    fun `sorting currency list by symbol field`() =
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val repository = SortRepository(mainCoroutineRule.testDispatcherProvider)
            val result = repository.sortInMemory(sampleRaw)
            assertThat(result).isEqualTo(sampleSorted)
        }
}