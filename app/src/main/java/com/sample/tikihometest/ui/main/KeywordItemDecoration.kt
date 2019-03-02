package com.sample.tikihometest.ui.main

import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

// TODO annotation
class KeywordItemDecoration constructor(
    private val marginSizeDp: Int
) : RecyclerView.ItemDecoration() {
    private val marginInPx: Int by lazy {
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            marginSizeDp.toFloat(),
            Resources.getSystem().displayMetrics
        ).roundToInt()
    }
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val isFirstChild = parent.getChildAdapterPosition(view) == 0
        with(outRect) {
            left = if (isFirstChild) {
                marginInPx
            } else {
                0
            }
            top = marginInPx
            right = marginInPx
            bottom = marginInPx
        }
    }
}