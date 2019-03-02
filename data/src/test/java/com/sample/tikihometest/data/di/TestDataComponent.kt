package com.sample.tikihometest.data.di

import com.sample.tikihometest.data.api.KeywordGithubApi
import com.sample.tikihometest.data.api.KeywordGithubApiModule
import com.sample.tikihometest.data.datasource.KeywordDataSource
import com.sample.tikihometest.data.datasource.KeywordDataSourceBindingModule
import com.sample.tikihometest.data.repository.KeywordRepositoryBindingModule
import com.sample.tikihometest.domain.repository.KeywordRepository
import dagger.Component

@Component(
    modules = [
        KeywordGithubApiModule::class,
        KeywordDataSourceBindingModule::class,
        KeywordRepositoryBindingModule::class
    ]
)
@DataScope
interface TestDataComponent {
    fun keywordGithubApi(): KeywordGithubApi
    fun keywordDataSource(): KeywordDataSource
    fun keywordRepository(): KeywordRepository
}