package com.nane.search.presentation.view.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nane.search.presentation.data.SearchViewType
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
        when (parent.adapter?.getItemViewType(position)) {
            SearchViewType.SEARCH_RECOMMENDATIONS -> {
                outRect.top = 16.toDp()
            }
            SearchViewType.SEARCH_PERFUMES -> {
                outRect.top = 24.toDp()
                outRect.bottom = 42.toDp()
            }
        }
    }
}