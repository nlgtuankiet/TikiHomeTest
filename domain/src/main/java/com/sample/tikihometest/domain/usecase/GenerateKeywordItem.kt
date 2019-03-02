package com.sample.tikihometest.domain.usecase

import com.sample.tikihometest.domain.di.DomainScope
import com.sample.tikihometest.domain.entity.KeywordItem
import com.sample.tikihometest.domain.util.Mockable
import javax.inject.Inject

@DomainScope
@Mockable
class GenerateKeywordItem @Inject constructor(
    private val splitKeyword: SplitKeyword,
    private val randomColor: RandomColor
) {
    /**
     * Transform the input [keyword] to a "presentable ready" keyword
     * including split keyword into 2 line and a background color (argb)
     *
     * @return a presentation ready form of keyword
     *
     * @see [KeywordItem]
     */
    operator fun invoke(keyword: String): KeywordItem {
        val spittedKeyword = splitKeyword(keyword)
        // generate dark color since the text is white
        val color = randomColor(0.75f, 0.35f)
        return KeywordItem(
            splitKeyword = spittedKeyword,
            color = color
        )
    }
}