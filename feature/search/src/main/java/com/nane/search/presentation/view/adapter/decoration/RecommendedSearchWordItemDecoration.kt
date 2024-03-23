package com.nane.search.presentation.view.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nanez.utils.util.toDp

class RecommendedSearchWordItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.layoutManager?.itemCount ?: 0

        when (position) {
            0 -> {
                outRect.left = 20.toDp()
                outRect.right = 8.toDp()
            }

            itemCount - 1 -> {
                outRect.right = 20.toDp()
            }

            else -> {
                outRect.right = 8.toDp()
            }
        }
    }
}