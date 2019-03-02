package com.sample.tikihometest.util

import android.view.LayoutInflater
import android.view.View

val View.inflater: LayoutInflater
    get() = LayoutInflater.from(this.context) ?: TODO()
