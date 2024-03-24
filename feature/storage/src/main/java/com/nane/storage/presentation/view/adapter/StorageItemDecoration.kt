package com.nane.storage.presentation.view.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nanez.utils.util.toDp

/**
 * Created by haul on 3/24/24
 */
class StorageItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val horizontalMargin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (position == 0 || position == 1) {
            outRect.top = 24.toDp()
        }

        when (column) {
            0 -> {
                outRect.left = horizontalMargin
                outRect.right = spacing / 2
            }

            else -> {
                outRect.left = spacing / 2
                outRect.right = horizontalMargin
            }
        }

        outRect.bottom = spacing * 2
    }
}