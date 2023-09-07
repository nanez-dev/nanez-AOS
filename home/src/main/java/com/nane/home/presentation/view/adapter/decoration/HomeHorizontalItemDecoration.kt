package com.nane.home.presentation.view.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nanez.utils.util.toDp

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeHorizontalItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val itemCount = parent.adapter?.itemCount ?: 0

        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.left = 16.toDp()
                outRect.right = 6.toDp()
            }
            itemCount - 1 -> {
                outRect.left = 6.toDp()
                outRect.right = 16.toDp()
            }
            else -> {
                outRect.left = 6.toDp()
                outRect.right = 6.toDp()
            }
        }
        outRect.bottom = 32.toDp()
    }
}