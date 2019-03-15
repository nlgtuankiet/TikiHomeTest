package com.sample.tikihometest.core

import androidx.lifecycle.ViewModel
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState

interface ViewModelFactory<S: MvRxState, VM : ViewModel> {
    fun create(initialState: S): VM
}