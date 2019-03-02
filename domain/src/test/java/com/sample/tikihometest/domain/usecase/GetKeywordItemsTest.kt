package com.sample.tikihometest.domain.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.sample.tikihometest.domain.entity.KeywordItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class GetKeywordItemsTest {
    private val getKeywords: GetKeywords = mock()
    private val generateKeywordItem: GenerateKeywordItem = mock()
    private lateinit var usecase: GetKeywordItems

    @Before
    fun `set up`() {
        reset(getKeywords)
        reset(generateKeywordItem)
    }

    @Test
    fun `fail when getKeywords throw exception`() = runBlocking {
        `when`(getKeywords.invoke()) doThrow RuntimeException("foo")
        usecase = GetKeywordItems(getKeywords, generateKeywordItem)

        val result = kotlin.runCatching { usecase() }

        Assert.assertTrue(result.isFailure)
        verifyNoMoreInteractions(generateKeywordItem)
    }
    @Test
    fun `success best case`() = runBlocking {
        val keywords = listOf("a", "blue", "c")
        `when`(getKeywords.invoke()) doReturn keywords
        `when`(generateKeywordItem.invoke("a")) doReturn KeywordItem("1", 1)
        `when`(generateKeywordItem.invoke("blue")) doReturn KeywordItem("2", 2)
        `when`(generateKeywordItem.invoke("c")) doReturn KeywordItem("3", 3)

        val expected = listOf(
            KeywordItem("1", 1),
            KeywordItem("2", 2),
            KeywordItem("3", 3)
        )
        usecase = GetKeywordItems(getKeywords, generateKeywordItem)

        val result = usecase()
        Assert.assertTrue(result == expected)
    }

    @Test
    fun `success mirror error case`() = runBlocking {
        val keywords = listOf("a", "blue", "c")
        `when`(getKeywords.invoke()) doReturn keywords
        `when`(generateKeywordItem.invoke("a")) doReturn KeywordItem("1", 1)
        `when`(generateKeywordItem.invoke("blue")) doThrow RuntimeException()
        `when`(generateKeywordItem.invoke("c")) doReturn KeywordItem("3", 3)
        val expected = listOf(
            KeywordItem("1", 1),
            KeywordItem("3", 3)
        )

        usecase = GetKeywordItems(getKeywords, generateKeywordItem)

        val result = usecase()
        Assert.assertTrue(result == expected)
    }

    @Test
    fun `success all error case`() = runBlocking {
        val keywords = listOf("a", "blue", "c")
        `when`(getKeywords.invoke()) doReturn keywords
        `when`(generateKeywordItem.invoke("a")) doThrow RuntimeException()
        `when`(generateKeywordItem.invoke("blue")) doThrow RuntimeException()
        `when`(generateKeywordItem.invoke("c")) doThrow RuntimeException()
        val expected = emptyList<KeywordItem>()
        usecase = GetKeywordItems(getKeywords, generateKeywordItem)

        val result = usecase()

        Assert.assertTrue(result == expected)
    }
}