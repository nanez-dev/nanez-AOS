package com.nane.home.presentation.view.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeGridItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val horizontalMargin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (spanCount == 2) {
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
        } else if (spanCount == 3) {
            when (column) {
                0 -> {
                    outRect.left = horizontalMargin
                    outRect.right = 0
                }

                1 -> {
                    outRect.left = spacing
                    outRect.right = spacing
                }

                else -> {
                    outRect.left = 0
                    outRect.right = horizontalMargin
                }
            }
        }

        outRect.bottom = spacing * 2
    }
}