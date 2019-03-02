package com.sample.tikihometest.data.repository

import com.sample.tikihometest.data.datasource.KeywordDataSource
import com.sample.tikihometest.data.di.DataScope
import com.sample.tikihometest.domain.repository.KeywordRepository
import javax.inject.Inject

@DataScope
class KeywordRepositoryImpl @Inject constructor(
    private val keywordDataSource: KeywordDataSource
) : KeywordRepository {
    override suspend fun getKeyWorks(): List<String> {
        return keywordDataSource.getKeywords()
    }
}