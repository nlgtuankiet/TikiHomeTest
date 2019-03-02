package com.sample.tikihometest.domain.entity

/**
 * a presentable keyword
 * @property splitKeyword keyword split into 2 lines if keyword contain more than 1 word
 * @property color background color
 * @see [com.sample.tikihometest.domain.usecase.SplitKeyword]
 */
data class KeywordItem(
    val splitKeyword: String,
    val color: Int
)