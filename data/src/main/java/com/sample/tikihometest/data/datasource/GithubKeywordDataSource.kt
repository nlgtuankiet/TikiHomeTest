package com.sample.tikihometest.data.datasource

import com.sample.tikihometest.data.api.KeywordGithubApi
import com.sample.tikihometest.data.di.DataScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@DataScope
class GithubKeywordDataSource @Inject constructor(
    private val keywordGithubApi: KeywordGithubApi
) : KeywordDataSource {
    override suspend fun getKeywords(): List<String> {
        return withContext(Dispatchers.IO) {
            keywordGithubApi.getKeywords()
        }
    }
}