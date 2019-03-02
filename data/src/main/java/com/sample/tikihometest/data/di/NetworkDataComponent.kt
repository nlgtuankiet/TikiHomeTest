package com.sample.tikihometest.data.di

import com.sample.tikihometest.data.api.KeywordGithubApiModule
import com.sample.tikihometest.data.datasource.KeywordDataSourceBindingModule
import com.sample.tikihometest.data.repository.KeywordRepositoryBindingModule
import dagger.Component

@Component(
    modules = [
        KeywordGithubApiModule::class,
        KeywordDataSourceBindingModule::class,
        KeywordRepositoryBindingModule::class
    ]
)
@DataScope
interface NetworkDataComponent : DataComponent {
    @Component.Builder
    interface Builder : DataComponent.Builder

    companion object {
        fun builder(): DataComponent.Builder = DaggerNetworkDataComponent.builder()
    }
}