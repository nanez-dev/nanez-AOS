package com.nane.search.presentation.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nane.search.presentation.data.SearchResultViewData

abstract class AbsSearchResultViewHolder<T: SearchResultViewData>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(data: T?)
}