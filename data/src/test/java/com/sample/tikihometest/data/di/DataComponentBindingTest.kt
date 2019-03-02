package com.sample.tikihometest.data.di

import com.sample.tikihometest.data.datasource.GithubKeywordDataSource
import com.sample.tikihometest.data.datasource.KeywordDataSource
import com.sample.tikihometest.data.repository.KeywordRepositoryImpl
import com.sample.tikihometest.domain.repository.KeywordRepository
import org.junit.Assert.assertTrue
import org.junit.Test

class DataComponentBindingTest {
    private val dataComponent: TestDataComponent = DaggerTestDataComponent.create()
    @Test
    fun `KeywordDataSource is GithubKeywordDataSource`() {
        val keywordDataSource: KeywordDataSource = dataComponent.keywordDataSource()
        assertTrue(keywordDataSource is GithubKeywordDataSource)
    }

    @Test
    fun `KeywordRepository is KeywordRepositoryImpl`() {
        val keywordRepository: KeywordRepository = dataComponent.keywordRepository()
        assertTrue(keywordRepository is KeywordRepositoryImpl)
    }
}