package com.nane.home.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.home.presentation.view.*
import com.nane.home.databinding.HomeListItemViewBinding
import com.nane.home.databinding.HomePagerItemViewBinding
import com.nane.home.databinding.HomeTitleItemViewBinding
import com.nane.home.presentation.data.HomeViewData
import com.nane.home.presentation.data.HomeViewType
import com.nane.home.presentation.data.PerfumeItemViewData

/**
 * Created by iseungjun on 2023/08/19
 */
class HomeMainAdapter : RecyclerView.Adapter<AbsHomeViewHolder<*>>() {

    private var dataList = listOf<HomeViewData>()

    var listener: UserActionListener? = null
    interface UserActionListener {
        fun onClickAccord(accordId: Int)
        fun onClickBrand(brandId: Int)
        fun onClickBannerView(moveToUrl: String?)
        fun onClickPerfume(data: PerfumeItemViewData?)
        fun onClickMore(@HomeViewType moreType: Int)
    }

    fun setDataList(data: List<HomeViewData>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsHomeViewHolder<*> {
        return when(viewType) {
            HomeViewType.HOME_PAGER_TYPE -> {
                HomePagerViewHolder(HomePagerItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
            }
            HomeViewType.HOME_TITLE_TYPE -> {
                HomeTitleViewHolder(HomeTitleItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            HomeViewType.HOME_HORI_LIST_TYPE -> {
                HomeHorizontalViewHolder(HomeListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
            }
            HomeViewType.HOME_RECOMMEND_TYPE -> {
                HomeRecommendPerfumeViewHolder(HomeListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
            }
            HomeViewType.HOME_BRAND_TYPE -> {
                HomeBrandViewHolder(HomeListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
            }
            HomeViewType.HOME_ACCORD_TYPE -> {
                HomeAccordViewHolder(HomeListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
            }
            else -> HomeEmptyViewHolder(View(parent.context))
        }
    }

    override fun getItemCount() = dataList.size
    override fun getItemViewType(position: Int) = dataList[position].viewType

    override fun onBindViewHolder(holder: AbsHomeViewHolder<*>, position: Int) {
        when(holder) {
            is HomePagerViewHolder -> {
                (dataList[position] as? HomeViewData.Banner)?.let {
                    holder.onBind(it)
                }
            }
            is HomeTitleViewHolder -> {
                (dataList[position] as? HomeViewData.MainTitle)?.let {
                    holder.onBind(it)
                }
            }
            is HomeHorizontalViewHolder -> {
                (dataList[position] as? HomeViewData.SpecialPerfume)?.let {
                    holder.onBind(it)
                }
            }
            is HomeRecommendPerfumeViewHolder -> {
                (dataList[position] as? HomeViewData.RecommondPerfume)?.let {
                    holder.onBind(it)
                }
            }
            is HomeBrandViewHolder -> {
                (dataList[position] as? HomeViewData.Brand)?.let {
                    holder.onBind(it)
                }
            }
            is HomeAccordViewHolder -> {
                (dataList[position] as? HomeViewData.Accord)?.let {
                    holder.onBind(it)
                }
            }
        }
    }
}