package com.sample.tikihometest.ui.main

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.sample.tikihometest.core.Event
import com.sample.tikihometest.domain.entity.KeywordItem

data class MainFragmentState(
    val isRefreshing: Boolean,
    val keywordItems: Async<List<KeywordItem>>,
    val errorEvent: Event<Int>?
) : MvRxState