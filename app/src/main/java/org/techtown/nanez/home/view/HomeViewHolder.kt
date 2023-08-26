package org.techtown.nanez.home.view

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import org.techtown.nanez.BR
import org.techtown.nanez.databinding.HomeListItemViewBinding
import org.techtown.nanez.databinding.HomePagerItemViewBinding
import org.techtown.nanez.databinding.HomeTitleItemViewBinding
import org.techtown.nanez.home.data.HomeViewData
import org.techtown.nanez.home.view.adapter.HomeAccordAdapter
import org.techtown.nanez.home.view.adapter.HomeBrandAdapter
import org.techtown.nanez.home.view.adapter.HomeHorizontalAdapter
import org.techtown.nanez.home.view.adapter.HomeRecommendPerfumeAdapter
import org.techtown.nanez.home.view.adapter.decoration.HomeGridItemDecoration
import org.techtown.nanez.home.view.adapter.decoration.HomeHorizontalItemDecoration
import org.techtown.nanez.utils.util.GlideImageLoadData
import org.techtown.nanez.utils.util.GlideUtil
import org.techtown.nanez.utils.util.dpToPx
import org.techtown.nanez.utils.util.toDp

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
        (binding.viewPager.adapter as? HomePagerAdapter)?.setDataList(data.bannerList)
    }


    private inner class HomePagerAdapter : PagerAdapter() {

        private var imgUrlList = listOf<String>()

        fun setDataList(dataList: List<String>) {
            imgUrlList = dataList
            notifyDataSetChanged()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = ImageView(container.context).apply {
                scaleType = ImageView.ScaleType.FIT_XY
                layoutParams ?: LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            }
            GlideUtil.instance.displayImage(GlideImageLoadData(imageView, imageUrl = imgUrlList[position]))
            container.addView(imageView)
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

class HomeTitleViewHolder(
    private val binding: HomeTitleItemViewBinding
) : AbsHomeViewHolder<HomeViewData.HomeTitleData>(binding.root) {

    override fun onBind(data: HomeViewData.HomeTitleData) {
        binding.setVariable(BR.viewData, data)
        binding.executePendingBindings()
    }
}


class HomeHorizontalViewHolder(
    private val binding: HomeListItemViewBinding
) : AbsHomeViewHolder<HomeViewData.HomeHorizontalData>(binding.root) {

    init {
        binding.recyclerView.apply {
            layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply {
                layoutManager = this
            }

            adapter ?: HomeHorizontalAdapter().apply {
                adapter = this
            }

            if (itemDecorationCount == 0) {
                addItemDecoration(HomeHorizontalItemDecoration())
            }
        }
    }

    override fun onBind(data: HomeViewData.HomeHorizontalData) {
        binding.txtTitle.text = data.title
        binding.btnMore.visibility = View.GONE
        (binding.recyclerView.adapter as? HomeHorizontalAdapter)?.setItemList(data.itemList)
    }
}


class HomeRecommendPerfumeViewHolder(
    private val binding: HomeListItemViewBinding
) : AbsHomeViewHolder<HomeViewData.HomeRecommendPerfumeData>(binding.root) {

    init {
        binding.recyclerView.apply {
            isNestedScrollingEnabled = false

            layoutManager ?: GridLayoutManager(context, 2).apply {
                layoutManager = this
            }

            adapter ?: HomeRecommendPerfumeAdapter().apply {
                adapter = this
            }

            if (itemDecorationCount == 0) {
                addItemDecoration(
                    HomeGridItemDecoration(
                        spanCount = 2,
                        spacing = 12.toDp(),
                        horizontalMargin = 16.toDp()
                    )
                )
            }
        }
    }

    override fun onBind(data: HomeViewData.HomeRecommendPerfumeData) {
        binding.txtTitle.text = data.title
        binding.btnMore.visibility = View.VISIBLE
        (binding.recyclerView.adapter as? HomeRecommendPerfumeAdapter)?.setItemList(data.itemList)
    }
}


class HomeBrandViewHolder(
    private val binding: HomeListItemViewBinding
) : AbsHomeViewHolder<HomeViewData.HomeBrandData>(binding.root) {

    init {
        binding.recyclerView.apply {
            isNestedScrollingEnabled = false
            layoutManager ?: GridLayoutManager(context, 2).apply {
                layoutManager = this
            }

            adapter ?: HomeBrandAdapter().apply {
                adapter = this
            }

            if (itemDecorationCount == 0) {
                addItemDecoration(
                    HomeGridItemDecoration(
                        spanCount = 2,
                        spacing = 12.toDp(),
                        horizontalMargin = 16.toDp()
                    )
                )
            }
        }
    }

    override fun onBind(data: HomeViewData.HomeBrandData) {
        binding.txtTitle.text = data.title
        binding.btnMore.visibility = View.VISIBLE
        (binding.recyclerView.adapter as? HomeBrandAdapter)?.setItemList(data.itemList)
    }
}


class HomeAccordViewHolder(
    private val binding: HomeListItemViewBinding
) : AbsHomeViewHolder<HomeViewData.HomeAccordData>(binding.root) {

    init {
        binding.recyclerView.apply {
            isNestedScrollingEnabled = false
            layoutManager ?: GridLayoutManager(context, 3).apply {
                layoutManager = this
            }

            adapter ?: HomeAccordAdapter().apply {
                adapter = this
            }

            if (itemDecorationCount == 0) {
                addItemDecoration(
                    HomeGridItemDecoration(
                        spanCount = 3,
                        spacing = 12.toDp(),
                        horizontalMargin = 16.toDp()
                    )
                )
            }
        }
    }

    override fun onBind(data: HomeViewData.HomeAccordData) {
        binding.txtTitle.text = data.title
        binding.btnMore.visibility = View.VISIBLE
        (binding.recyclerView.adapter as? HomeAccordAdapter)?.setItemList(data.itemList)
    }
}


class HomeEmptyViewHolder(itemView: View) : AbsHomeViewHolder<HomeViewData>(itemView) {
    override fun onBind(data: HomeViewData) {}
}

abstract class AbsHomeViewHolder<T : HomeViewData>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(data: T)
}