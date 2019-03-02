package com.sample.tikihometest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.tikihometest.R
import com.sample.tikihometest.core.Event
import com.sample.tikihometest.domain.entity.KeywordItem
import com.sample.tikihometest.domain.usecase.GetKeywordItems
import com.sample.tikihometest.util.postValueIfNew
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getKeywordItems: GetKeywordItems
) : ViewModel() {
    private var loadKeywordJob: Job? = null

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _keywordItems = MutableLiveData<List<KeywordItem>>()
    val keywordItems: LiveData<List<KeywordItem>>
        get() = _keywordItems

    private val _errorEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>>
        get() = _errorEvent

    init {
        loadKeywordItems()
    }

    fun loadKeywordItems() {
        loadKeywordJob?.cancel()
        loadKeywordJob = viewModelScope.launch {
            _isRefreshing.postValueIfNew(true)
            kotlin.runCatching { getKeywordItems() }
                .onFailure {
                    // TODO Log
                    _errorEvent.postValue(Event(R.string.main_load_keyword_failed))
                    _keywordItems.postValue(null)
                }
                .onSuccess {
                    _keywordItems.postValue(it)
                }
            _isRefreshing.postValueIfNew(false)
        }
    }

    fun onSwipeRefresh() {
        loadKeywordItems()
    }

    override fun onCleared() {
        super.onCleared()
        loadKeywordJob?.cancel()
    }
}
