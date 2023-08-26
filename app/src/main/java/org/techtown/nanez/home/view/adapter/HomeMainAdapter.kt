package org.techtown.nanez.home.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nanez.databinding.HomeListItemViewBinding
import org.techtown.nanez.databinding.HomePagerItemViewBinding
import org.techtown.nanez.databinding.HomeTitleItemViewBinding
import org.techtown.nanez.home.data.HomeViewData
import org.techtown.nanez.home.data.HomeViewType
import org.techtown.nanez.home.view.*
import org.techtown.nanez.home.view.HomeHorizontalViewHolder

/**
 * Created by iseungjun on 2023/08/19
 */
class HomeMainAdapter : RecyclerView.Adapter<AbsHomeViewHolder<*>>() {

    private var dataList = listOf<HomeViewData>()

    fun setDataList(data: List<HomeViewData>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsHomeViewHolder<*> {
        return when(viewType) {
            HomeViewType.HOME_PAGER_TYPE -> {
                HomePagerViewHolder(HomePagerItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            HomeViewType.HOME_TITLE_TYPE -> {
                HomeTitleViewHolder(HomeTitleItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            HomeViewType.HOME_HORI_LIST_TYPE -> {
                HomeHorizontalViewHolder(HomeListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            HomeViewType.HOME_RECOMMEND_TYPE -> {
                HomeRecommendPerfumeViewHolder(HomeListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            HomeViewType.HOME_BRAND_TYPE -> {
                HomeBrandViewHolder(HomeListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            HomeViewType.HOME_ACCORD_TYPE -> {
                HomeAccordViewHolder(HomeListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> HomeEmptyViewHolder(View(parent.context))
        }
    }

    override fun getItemCount() = dataList.size
    override fun getItemViewType(position: Int) = dataList[position].viewType

    override fun onBindViewHolder(holder: AbsHomeViewHolder<*>, position: Int) {
        when(holder) {
            is HomePagerViewHolder -> {
                (dataList[position] as? HomeViewData.HomeBannerData)?.let {
                    holder.onBind(it)
                }
            }
            is HomeTitleViewHolder -> {
                (dataList[position] as? HomeViewData.HomeTitleData)?.let {
                    holder.onBind(it)
                }
            }
            is HomeHorizontalViewHolder -> {
                (dataList[position] as? HomeViewData.HomeHorizontalData)?.let {
                    holder.onBind(it)
                }
            }
            is HomeRecommendPerfumeViewHolder -> {
                (dataList[position] as? HomeViewData.HomeRecommendPerfumeData)?.let {
                    holder.onBind(it)
                }
            }
            is HomeBrandViewHolder -> {
                (dataList[position] as? HomeViewData.HomeBrandData)?.let {
                    holder.onBind(it)
                }
            }
            is HomeAccordViewHolder -> {
                (dataList[position] as? HomeViewData.HomeAccordData)?.let {
                    holder.onBind(it)
                }
            }
        }
    }
}