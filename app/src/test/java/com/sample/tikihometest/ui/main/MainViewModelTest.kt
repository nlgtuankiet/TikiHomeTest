package com.sample.tikihometest.ui.main

import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.withState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.sample.tikihometest.R
import com.sample.tikihometest.domain.entity.KeywordItem
import com.sample.tikihometest.domain.usecase.GetKeywordItems
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {
    private val getKeywordItems: GetKeywordItems = mock()
    private lateinit var mainViewModel: MainViewModel
    private val initState = MainFragmentState(
        isRefreshing = true,
        errorEvent = null,
        keywordItems = Uninitialized
    )

    @Before
    fun setup() {
        reset(getKeywordItems)
    }

    @Test
    fun `loadKeywordItems success`() = runBlocking {
        val keywordItems = listOf(
            KeywordItem("a", 1),
            KeywordItem("blue", 2)
        )
        `when`(getKeywordItems.invoke()) doReturn keywordItems
        mainViewModel = MainViewModel(initState, getKeywordItems)
        mainViewModel.loadKeywordItems()

        withState(mainViewModel) { state ->
            Assert.assertEquals(keywordItems, state.keywordItems())
        }
    }

    @Test
    fun `loadKeywordItems fail`() = runBlocking {
        `when`(getKeywordItems.invoke()) doThrow RuntimeException()
        mainViewModel = MainViewModel(initState, getKeywordItems)
        mainViewModel.loadKeywordItems()

        withState(mainViewModel) { state ->
            Assert.assertTrue(state.keywordItems is Fail)
            Assert.assertEquals(
                R.string.main_load_keyword_failed,
                state.errorEvent?.getContentIfNotHandled()
            )
        }
    }
}