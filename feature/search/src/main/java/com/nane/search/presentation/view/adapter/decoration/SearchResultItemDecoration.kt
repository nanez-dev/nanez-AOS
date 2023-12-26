package com.nane.search.presentation.view.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nanez.utils.util.toDp

class SearchResultItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        if (position%2 == 0) {
            outRect.left = 20.toDp()
            outRect.right = 7.toDp()
        } else {
            outRect.right = 20.toDp()
        }
    }
}