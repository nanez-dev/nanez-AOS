package com.nane.theme.presentation.view.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nanez.utils.util.toDp

class PopularAccordItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        when (position) {
            0 -> {
                outRect.left = 20.toDp()
                outRect.right = 4.toDp()
            }

            itemCount - 1 -> {
                outRect.left = 4.toDp()
                outRect.right = 20.toDp()
            }

            else -> {
                outRect.left = 4.toDp()
                outRect.right = 4.toDp()
            }
        }
    }
}