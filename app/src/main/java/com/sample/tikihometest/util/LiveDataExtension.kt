package com.sample.tikihometest.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sample.tikihometest.core.Event

@MainThread
inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandled: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { event ->
        event?.getContentIfNotHandled()?.let(onEventUnhandled)
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

fun <T> MutableLiveData<T>.postValueIfNew(newValue: T) {
    if (this.value != newValue) postValue(newValue)
}
