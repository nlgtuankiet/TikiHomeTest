package com.sample.tikihometest.data.datasource

import dagger.Binds
import dagger.Module

@Module
interface KeywordDataSourceBindingModule {
    @Binds
    fun bindKeywordDataSource(keywordDataSource: GithubKeywordDataSource): KeywordDataSource
}