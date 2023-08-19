package org.techtown.nanez.home.view

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import org.techtown.nanez.databinding.HomePagerItemViewBinding
import org.techtown.nanez.home.data.HomeViewData

/**
 * Created by iseungjun on 2023/08/19
 */

class HomePagerViewHolder(
    private val binding: HomePagerItemViewBinding
) : AbsHomeViewHolder<HomeViewData.HomeBannerData>(binding.root) {

    init {
        binding.viewPager.apply {
            adapter = HomePagerAdapter()
        }
        binding.indicator.attachTo(binding.viewPager)
    }

    override fun onBind(data: HomeViewData.HomeBannerData) {
        (binding.viewPager.adapter as? HomePagerAdapter)?.setDataList(data.bannerList ?: emptyList())
    }


    private inner class HomePagerAdapter : PagerAdapter() {

        private var imgUrlList = listOf<String>()

        fun setDataList(dataList: List<String>) {
            imgUrlList = dataList
            notifyDataSetChanged()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = ImageView(container.context).apply {
                layoutParams ?: LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            }

            return imageView
        }

        override fun getCount() = imgUrlList.size
        override fun isViewFromObject(view: View, `object`: Any) = view == `object`

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            if (`object` is View) {
                container.removeView(`object`)
            }
        }
    }
}


class HomeEmptyViewHolder(itemView: View) : AbsHomeViewHolder<HomeViewData>(itemView) {
    override fun onBind(data: HomeViewData) {}
}

abstract class AbsHomeViewHolder<T : HomeViewData>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(data: T)
}