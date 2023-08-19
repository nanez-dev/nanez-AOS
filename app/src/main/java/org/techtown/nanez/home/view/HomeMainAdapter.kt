package org.techtown.nanez.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nanez.databinding.HomePagerItemViewBinding
import org.techtown.nanez.home.data.HomeViewData
import org.techtown.nanez.home.data.HomeViewType

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
        }
    }
}