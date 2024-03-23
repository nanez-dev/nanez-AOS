package com.nane.search.presentation.view.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nane.search.presentation.data.SearchViewType
import org.techtown.nanez.utils.util.toDp

class SearchResultItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        when (parent.adapter?.getItemViewType(position)) {
            SearchViewType.RECOMMENDATION_TYPE -> {
                outRect.bottom = 24.toDp()
            }

            else -> {
                if (position % 2 == 1) {
                    outRect.left = 20.toDp()
                    outRect.right = (3.5).toDp()
                    if (position <= (parent.adapter?.itemCount?.minus(2) ?: 0)) {
                        outRect.bottom = 16.toDp()
                    }
                } else {
                    outRect.left = (3.5).toDp()
                    outRect.right = 20.toDp()
                    if (position <= (parent.adapter?.itemCount?.minus(2) ?: 0)) {
                        outRect.bottom = 16.toDp()
                    }
                }
            }
        }
    }
}