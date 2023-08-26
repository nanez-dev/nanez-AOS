package org.techtown.nanez.home.view.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nanez.home.data.HomeViewType
import org.techtown.nanez.utils.util.toDp

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeMainItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        when (parent.adapter?.getItemViewType(position)) {
            HomeViewType.HOME_PAGER_TYPE -> {
                outRect.top = 16.toDp()
            }
            HomeViewType.HOME_TITLE_TYPE -> {
                outRect.top = 16.toDp()
            }
            HomeViewType.HOME_HORI_LIST_TYPE -> {
                outRect.top = 52.toDp()
            }
            else -> {
                outRect.top = 32.toDp()
            }
        }
    }
}