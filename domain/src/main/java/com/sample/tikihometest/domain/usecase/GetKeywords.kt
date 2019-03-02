package com.sample.tikihometest.domain.usecase

import com.sample.tikihometest.domain.di.DomainScope
import com.sample.tikihometest.domain.repository.KeywordRepository
import com.sample.tikihometest.domain.util.Mockable
import javax.inject.Inject

@DomainScope
@Mockable
class GetKeywords @Inject constructor(
    private val keywordRepository: KeywordRepository
) {
    /**
     * Get keyword list from [keywordRepository]
     */
    suspend operator fun invoke(): List<String> {
        return keywordRepository.getKeyWorks()
    }
}