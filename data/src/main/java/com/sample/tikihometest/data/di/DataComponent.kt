package com.sample.tikihometest.data.di

import com.sample.tikihometest.domain.repository.KeywordRepository

interface DataComponent {
    fun provideKeywordRepository(): KeywordRepository
    interface Builder {
        fun build(): DataComponent
    }
}