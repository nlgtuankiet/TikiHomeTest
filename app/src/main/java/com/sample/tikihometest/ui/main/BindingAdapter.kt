package com.sample.tikihometest.ui.main

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @BindingAdapter("backgroundTintArbg")
    @JvmStatic
    fun bindRbgColor(imageView: ImageView, rbgIntValue: Int) {
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(rbgIntValue))
    }
}
