package com.sample.tikihometest.data.repository

import com.sample.tikihometest.domain.repository.KeywordRepository
import dagger.Binds
import dagger.Module

@Module
interface KeywordRepositoryBindingModule {
    @Binds
    fun keywordRepository(keywordRepository: KeywordRepositoryImpl): KeywordRepository
}