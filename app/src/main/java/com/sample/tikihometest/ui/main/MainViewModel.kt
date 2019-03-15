package com.sample.tikihometest.ui.main

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.ViewModelContext
import com.sample.tikihometest.BuildConfig
import com.sample.tikihometest.R
import com.sample.tikihometest.core.Event
import com.sample.tikihometest.core.ViewModelFactory
import com.sample.tikihometest.domain.usecase.GetKeywordItems
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel @AssistedInject constructor(
    @Assisted initialState: MainFragmentState,
    private val getKeywordItems: GetKeywordItems
) : BaseMvRxViewModel<MainFragmentState>(
    initialState = initialState,
    debugMode = BuildConfig.DEBUG
) {

    @AssistedInject.Factory
    interface Factory : ViewModelFactory<MainFragmentState, MainViewModel>

    private var loadKeywordJob: Job? = null

    init {
        loadKeywordItems()
    }

    fun loadKeywordItems() {
        loadKeywordJob?.cancel()
        loadKeywordJob = viewModelScope.launch {
            setState { copy(isRefreshing = true) }
            kotlin.runCatching { getKeywordItems() }
                .onFailure {
                    // TODO Log
                    setState {
                        copy(
                            isRefreshing = false,
                            keywordItems = Fail(it),
                            errorEvent = Event(R.string.main_load_keyword_failed)
                        )
                    }
                }
                .onSuccess {
                    setState {
                        copy(
                            isRefreshing = false,
                            keywordItems = Success(it)
                        )
                    }
                }
        }
    }

    fun onSwipeRefresh() {
        loadKeywordItems()
    }

    override fun onCleared() {
        super.onCleared()
        loadKeywordJob?.cancel()
    }

    companion object : MvRxViewModelFactory<MainViewModel, MainFragmentState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: MainFragmentState
        ): MainViewModel? {
            val fragmentContext = viewModelContext as? FragmentViewModelContext ?: TODO()
            val fragment = fragmentContext.fragment as? MainFragment ?: TODO()
            return fragment.viewModelFactory.create(state)
        }

        override fun initialState(viewModelContext: ViewModelContext): MainFragmentState? {
            return MainFragmentState(
                isRefreshing = true,
                errorEvent = null,
                keywordItems = Uninitialized
            )
        }
    }
}
