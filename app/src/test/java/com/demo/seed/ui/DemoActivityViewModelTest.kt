package com.demo.seed.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.demo.seed.MainCoroutineRule
import com.demo.seed.data.CurrencyRepository
import com.demo.seed.data.SortRepository
import com.demo.seed.getOrAwaitValue
import com.demo.seed.observeForTesting
import com.demo.seed.ui.model.CurrencyInfo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DemoActivityViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var demoActivityViewModel: DemoActivityViewModel

    private val mockCurrencyRepository: CurrencyRepository = mock()
    private val mockSortRepository: SortRepository = mock()

    private val sampleRawList = listOf(
        CurrencyInfo("C", "C", "C"),
        CurrencyInfo("A", "A", "A"),
        CurrencyInfo("B", "B", "B"),
    )

    private val sampleSortedList = listOf(
        CurrencyInfo("A", "A", "A"),
        CurrencyInfo("B", "B", "B"),
        CurrencyInfo("C", "C", "C"),
    )

    @Before
    fun setUp() {
        demoActivityViewModel = DemoActivityViewModel(mockCurrencyRepository, mockSortRepository)
    }

    @Test
    fun `GIVEN a list of currency, WHEN the user selects an item THEN currencyInfoSelected will be updated`() {
        val observer: Observer<CurrencyInfo> = mock()
        demoActivityViewModel.currencyInfoSelected.observeForever(observer)
        demoActivityViewModel.updateSelectedCurrencyInfo(CurrencyInfo("A", "A", "A"))
        verify(observer).onChanged(CurrencyInfo("A", "A", "A"))
    }

    @Test
    fun `GIVEN it is empty, WHEN the user load data THEN a raw list will be generated`() =
        mainCoroutineRule.testDispatcher.runBlockingTest {
            whenever(mockCurrencyRepository.execute()).thenReturn(sampleRawList)
            demoActivityViewModel.loadCurrencyList()
            demoActivityViewModel.currencyList.observeForTesting {
                assertThat(demoActivityViewModel.currencyList.getOrAwaitValue()).isEqualTo(
                    sampleRawList
                )
            }
        }

    @Test
    fun `GIVEN a list of currency, WHEN the user sorts THEN a sorted list will be generated`() =
        mainCoroutineRule.testDispatcher.runBlockingTest {
            whenever(mockCurrencyRepository.execute()).thenReturn(sampleRawList)
            whenever(mockSortRepository.sortInMemory(sampleRawList)).thenReturn(sampleSortedList)
            demoActivityViewModel.loadCurrencyList()
            demoActivityViewModel.sortCurrencyList()
            demoActivityViewModel.currencyList.observeForTesting {
                assertThat(demoActivityViewModel.currencyList.getOrAwaitValue()).isEqualTo(
                    sampleSortedList
                )
            }
        }


}