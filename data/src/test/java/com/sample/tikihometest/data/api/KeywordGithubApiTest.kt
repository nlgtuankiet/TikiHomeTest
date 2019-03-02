package com.sample.tikihometest.data.api

import com.sample.tikihometest.data.TestData
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class KeywordGithubApiTest {
    private val keywordGithubApi: KeywordGithubApi = Retrofit.Builder()
        .baseUrl(KeywordGithubApi.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(KeywordGithubApi::class.java)

    @Test
    fun `getKeywords() return same content from network`() = runBlocking {
        val expected: List<String> = TestData.keywordsFromKeywordsJson
        val actual: List<String> = keywordGithubApi.getKeywords()

        Assert.assertEquals(expected, actual)
    }
}