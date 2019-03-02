package com.sample.tikihometest.domain.di

import com.sample.tikihometest.domain.repository.KeywordRepository
import com.sample.tikihometest.domain.usecase.GetKeywordItems
import dagger.BindsInstance
import dagger.Component

@Component
@DomainScope
abstract class DomainComponent {
    abstract fun provideGetKeywordItems(): GetKeywordItems

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun keywordRepository(repository: KeywordRepository): Builder
        fun build(): DomainComponent
    }
    companion object {
        fun builder(): Builder = DaggerDomainComponent.builder()
    }
}