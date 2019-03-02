package com.sample.tikihometest.domain.usecase

import com.sample.tikihometest.domain.di.DomainScope
import com.sample.tikihometest.domain.entity.KeywordItem
import com.sample.tikihometest.domain.util.Mockable
import javax.inject.Inject

@DomainScope
@Mockable
class GetKeywordItems @Inject constructor(
    private val getKeywords: GetKeywords,
    private val generateKeywordItem: GenerateKeywordItem
) {
    /**
     * Get a presentable keyword list
     *
     * Assume that keywords from [getKeywords] may contain invalids keyword
     * this use case only select valid one an ignores others
     * if all is invalid return a empty list
     *
     * e.g.  ["a", "  ", "blue", "c"] => ["a", "blue", "c"]
     *
     * @return [KeywordItem] list of valid keywords
     */
    suspend operator fun invoke(): List<KeywordItem> {
        val keywords = getKeywords()
        return keywords
            .map { keyword -> kotlin.runCatching { generateKeywordItem(keyword) } }
            .filter { it.isSuccess }
            .map { it.getOrThrow() }
    }
}