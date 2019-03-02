package com.sample.tikihometest.data.datasource

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.sample.tikihometest.data.api.KeywordGithubApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class GithubKeywordDataSourceTest {
    private val keywordGithubApi: KeywordGithubApi = mock()
    private lateinit var githubKeywordDataSource: GithubKeywordDataSource

    @Before
    fun setup() {
        reset(keywordGithubApi)
    }

    @Test
    fun `getKeywords return expected data`() = runBlocking {
        val expected: List<String> = listOf("a", "blue")
        val getKeywordsMockResult = listOf("a", "blue")
        `when`(keywordGithubApi.getKeywords()) doReturn getKeywordsMockResult
        githubKeywordDataSource = GithubKeywordDataSource(keywordGithubApi)

        val actual = githubKeywordDataSource.getKeywords()

        Assert.assertEquals(expected, actual)
    }
}