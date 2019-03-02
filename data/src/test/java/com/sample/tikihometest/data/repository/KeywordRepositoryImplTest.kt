package com.sample.tikihometest.data.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.sample.tikihometest.data.datasource.KeywordDataSource
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class KeywordRepositoryImplTest {
    private val keywordDataSource: KeywordDataSource = mock()
    private lateinit var keywordRepositoryImpl: KeywordRepositoryImpl

    @Before
    fun `set up`() {
        keywordRepositoryImpl = KeywordRepositoryImpl(
            keywordDataSource = keywordDataSource
        )
    }

    @After
    fun `tear down`() {
        reset(keywordDataSource)
    }

    @Test
    fun `getKeyWorks return expected data`() = runBlocking {
        // given
        `when`(keywordDataSource.getKeywords()) doReturn listOf("a", "blue")
        val expected: List<String> = listOf(
            "a", "blue"
        )

        // when
        val actual: List<String> = keywordRepositoryImpl.getKeyWorks()

        // then
        Assert.assertEquals(expected, actual)
        // TODO assert more strictly
    }
}