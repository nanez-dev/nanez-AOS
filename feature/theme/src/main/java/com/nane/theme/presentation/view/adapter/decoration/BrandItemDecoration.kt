package com.nane.theme.presentation.view.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nane.theme.presentation.data.BrandViewType
import org.techtown.nanez.utils.util.toDp

class BrandItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        when (parent.adapter?.getItemViewType(position)) {
            BrandViewType.ALL_BRAND_ITEM_TYPE -> {
                if (position%2 == 0) {
                    outRect.left = 20.toDp()
                    outRect.right = (3.5).toDp()
                } else {
                    outRect.left = (3.5).toDp()
                    outRect.right = 20.toDp()
                }

                if (position in 0..itemCount-3) {
                    outRect.bottom = 16.toDp()
                }
            }
        }
    }
}