package com.nane.theme.presentation.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nane.theme.presentation.data.AccordViewData

abstract class AbsAccordViewHolder<T: AccordViewData>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(data: T?)
}