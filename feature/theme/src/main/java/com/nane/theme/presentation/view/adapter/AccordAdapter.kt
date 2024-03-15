package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.nane.theme.BR
import com.nane.theme.databinding.ThemeAllAccordItemViewBinding
import com.nane.theme.databinding.ThemeAllAccordTitleItemViewBinding
import com.nane.theme.databinding.ThemePopularAccordListItemViewBinding
import com.nane.theme.presentation.data.AccordViewData
import com.nane.theme.presentation.data.AccordViewType
import com.nane.theme.presentation.view.AbsAccordViewHolder
import com.nane.theme.presentation.view.adapter.decoration.PopularAccordItemDecoration

class AccordAdapter : Adapter<AbsAccordViewHolder<*>>() {

    private var itemList: List<AccordViewData> = emptyList()

    fun setItemList(list: List<AccordViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return itemList.getOrNull(position)?.viewType ?: AccordViewType.ALL_ACCORD_ITEM_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsAccordViewHolder<*> {
        return when (viewType) {
            AccordViewType.POPULAR_ITEM_LIST_TYPE -> {
                PopularAccordViewHolder(
                    ThemePopularAccordListItemViewBinding
                        .inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                )
            }

            AccordViewType.TITLE_TYPE -> {
                AllAccordTitleViewHolder(
                    ThemeAllAccordTitleItemViewBinding
                        .inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                )
            }

            else -> {
                AllAccordViewHolder(
                    ThemeAllAccordItemViewBinding
                        .inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: AbsAccordViewHolder<*>, position: Int) {
        when (holder) {
            is PopularAccordViewHolder -> {
                holder.onBind(itemList.getOrNull(position) as? AccordViewData.PopularAccordItemListViewData)
            }

            is AllAccordTitleViewHolder -> {
                holder.onBind(itemList.getOrNull(position) as? AccordViewData.AccordTitleViewData)
            }

            is AllAccordViewHolder -> {
                holder.onBind(itemList.getOrNull(position) as? AccordViewData.AllAccordItemViewData)
            }
        }
    }

    inner class PopularAccordViewHolder(
        private val binding: ThemePopularAccordListItemViewBinding
    ): AbsAccordViewHolder<AccordViewData.PopularAccordItemListViewData>(binding.root) {

        override fun onBind(data: AccordViewData.PopularAccordItemListViewData?) {
            data ?: return

            with(binding.rvPopularAccords) {
                adapter ?: PopularAccordsAdapter().apply {
                    setItemList(data.accordItemList)
                    setOnItemClickListener(onAccordItemClickListener)
                    adapter = this
                }
                layoutManager ?: LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                ).apply { layoutManager = this }

                if (itemDecorationCount == 0) addItemDecoration(PopularAccordItemDecoration())
            }
        }
    }

    inner class AllAccordTitleViewHolder(
        private val binding: ThemeAllAccordTitleItemViewBinding
    ): AbsAccordViewHolder<AccordViewData.AccordTitleViewData>(binding.root) {

        override fun onBind(data: AccordViewData.AccordTitleViewData?) {}
    }

    inner class AllAccordViewHolder(
        private val binding: ThemeAllAccordItemViewBinding
    ): AbsAccordViewHolder<AccordViewData.AllAccordItemViewData>(binding.root) {

        init {
            binding.root.setOnClickListener {
                onAccordItemClickListener?.onAllAccordItemClick(
                    (itemList.getOrNull(adapterPosition) as AccordViewData.AllAccordItemViewData).id
                )
            }
        }

        override fun onBind(data: AccordViewData.AllAccordItemViewData?) {
            data ?: return

            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }
    }

    private var onAccordItemClickListener: AccordItemClickListener? = null
    fun setOnAccordItemClickListener(itemClickListener: AccordItemClickListener) {
        onAccordItemClickListener = itemClickListener
    }

    interface AccordItemClickListener {
        fun onPopularAccordItemClick(itemId: Int)

        fun onAllAccordItemClick(itemId: Int)
    }
}