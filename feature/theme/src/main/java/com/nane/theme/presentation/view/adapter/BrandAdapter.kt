package com.nane.theme.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.nane.theme.BR
import com.nane.theme.databinding.ThemeAllBrandItemViewBinding
import com.nane.theme.databinding.ThemeAllBrandTitleItemViewBinding
import com.nane.theme.databinding.ThemePopularBrandListItemViewBinding
import com.nane.theme.presentation.data.BrandViewData
import com.nane.theme.presentation.data.BrandViewType
import com.nane.theme.presentation.view.AbsBrandViewHolder
import com.nane.theme.presentation.view.adapter.decoration.PopularBrandItemDecoration

class BrandAdapter : Adapter<AbsBrandViewHolder<*>>() {

    private var itemList: List<BrandViewData> = emptyList()

    fun setItemList(list: List<BrandViewData>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return itemList.getOrNull(position)?.viewType ?: BrandViewType.ALL_BRAND_ITEM_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsBrandViewHolder<*> {
        return when (viewType) {
            BrandViewType.POPULAR_ITEM_LIST_TYPE -> {
                PopularBrandViewHolder(
                    ThemePopularBrandListItemViewBinding
                        .inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                )
            }

            BrandViewType.TITLE_TYPE -> {
                AllBrandTitleViewHolder(
                    ThemeAllBrandTitleItemViewBinding
                        .inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                )
            }

            else -> {
                AllBrandViewHolder(
                    ThemeAllBrandItemViewBinding
                        .inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: AbsBrandViewHolder<*>, position: Int) {
        when (holder) {
            is PopularBrandViewHolder -> {
                holder.onBind(itemList.getOrNull(position) as? BrandViewData.PopularBrandItemListViewData)
            }

            is AllBrandTitleViewHolder -> {
                holder.onBind(itemList.getOrNull(position) as? BrandViewData.BrandTitleViewData)
            }

            is AllBrandViewHolder -> {
                holder.onBind(itemList.getOrNull(position) as? BrandViewData.AllBrandItemViewData)
            }
        }
    }

    inner class PopularBrandViewHolder(
        private val binding: ThemePopularBrandListItemViewBinding
    ) : AbsBrandViewHolder<BrandViewData.PopularBrandItemListViewData>(binding.root) {

        override fun onBind(data: BrandViewData.PopularBrandItemListViewData?) {
            data ?: return

            with(binding.rvPopularBrands) {
                adapter ?: PopularBrandsAdapter().apply { adapter = this }
                (adapter as PopularBrandsAdapter).apply {
                    setItemList(data.brandItemList)
                    setOnItemClickListener(onBrandItemClickListener)
                }
                layoutManager ?: LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                ).apply { layoutManager = this }

                if (itemDecorationCount == 0) addItemDecoration(PopularBrandItemDecoration())
            }
        }
    }

    inner class AllBrandTitleViewHolder(
        private val binding: ThemeAllBrandTitleItemViewBinding
    ) : AbsBrandViewHolder<BrandViewData.BrandTitleViewData>(binding.root) {

        override fun onBind(data: BrandViewData.BrandTitleViewData?) {}
    }

    inner class AllBrandViewHolder(
        private val binding: ThemeAllBrandItemViewBinding
    ) : AbsBrandViewHolder<BrandViewData.AllBrandItemViewData>(binding.root) {

        init {
            binding.root.setOnClickListener {
                onBrandItemClickListener?.onAllBrandItemClick((itemList.getOrNull(adapterPosition) as BrandViewData.AllBrandItemViewData).id)
            }
        }

        override fun onBind(data: BrandViewData.AllBrandItemViewData?) {
            data ?: return

            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()
        }

    }

    private var onBrandItemClickListener: BrandItemClickListener? = null
    fun setOnBrandItemClickListener(itemClickListener: BrandItemClickListener) {
        onBrandItemClickListener = itemClickListener
    }

    interface BrandItemClickListener {
        fun onPopularBrandItemClick(itemId: Int)

        fun onAllBrandItemClick(itemId: Int)
    }
}