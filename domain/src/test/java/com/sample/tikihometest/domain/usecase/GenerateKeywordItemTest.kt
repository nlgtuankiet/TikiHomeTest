package com.sample.tikihometest.domain.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.sample.tikihometest.domain.entity.KeywordItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class GenerateKeywordItemTest {
    private val splitKeyword: SplitKeyword = mock()
    private val randomColor: RandomColor = mock()
    private lateinit var usecase: GenerateKeywordItem

    @Before
    fun `set up`() {
        reset(splitKeyword)
        reset(randomColor)
    }

    @Test
    fun `fail when splitKeyword throw exception`() {
        val keyword = "bar"
        `when`(splitKeyword.invoke(keyword)) doThrow RuntimeException("foo")
        usecase = GenerateKeywordItem(splitKeyword, randomColor)

        val result = kotlin.runCatching { usecase(keyword) }
        assertTrue(result.isFailure)
        verifyNoMoreInteractions(randomColor)
    }

    @Test
    fun `success`() {
        val keyword = "bar"
        val keywordSplited = "foo"
        val color = 123
        val expected = KeywordItem(color = color, splitKeyword = keywordSplited)
        `when`(splitKeyword.invoke(keyword)) doReturn keywordSplited
        `when`(randomColor.invoke(0.75f, 0.35f)) doReturn color
        usecase = GenerateKeywordItem(splitKeyword, randomColor)

        val result = usecase(keyword)

        assertEquals(expected, result)
    }
}