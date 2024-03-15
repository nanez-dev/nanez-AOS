package com.nane.theme.presentation.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nane.theme.presentation.data.BrandViewData

abstract class AbsBrandViewHolder<T: BrandViewData>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(data: T?)
}