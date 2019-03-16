package com.sample.tikihometest.ui.main

import com.airbnb.epoxy.TypedEpoxyController
import com.sample.tikihometest.domain.entity.KeywordItem
import com.sample.tikihometest.mainKeywordItem

class KeywordItemController : TypedEpoxyController<List<KeywordItem>>() {
    override fun buildModels(data: List<KeywordItem>?) {
        data?.forEach { keywordItem ->
            mainKeywordItem {
                id(keywordItem.splitKeyword)
                item(keywordItem)
            }
        }
    }
}