package com.nane.home.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBindings
import androidx.viewpager.widget.PagerAdapter
import com.nane.home.BR
import com.nane.home.R
import com.nane.home.databinding.HomeListItemViewBinding
import com.nane.home.databinding.HomePagerImageViewBinding
import com.nane.home.databinding.HomePagerItemViewBinding
import com.nane.home.databinding.HomeTitleItemViewBinding
import com.nane.home.presentation.data.HomeViewData
import com.nane.home.presentation.data.HomeViewType
import com.nane.home.presentation.view.adapter.HomeAccordAdapter
import com.nane.home.presentation.view.adapter.HomeBrandAdapter
import com.nane.home.presentation.view.adapter.HomeHorizontalAdapter
import com.nane.home.presentation.view.adapter.HomeMainAdapter
import com.nane.home.presentation.view.adapter.HomeRecommendPerfumeAdapter
import com.nane.home.presentation.view.adapter.decoration.HomeGridItemDecoration
import com.nane.home.presentation.view.adapter.decoration.HomeHorizontalItemDecoration
import org.techtown.nanez.utils.util.GlideImageLoadData
import org.techtown.nanez.utils.util.GlideUtil
import org.techtown.nanez.utils.util.toDp
import kotlin.math.ceil

/**
 * Created by iseungjun on 2023/08/19
 */

class HomePagerViewHolder(
    private val binding: HomePagerItemViewBinding,
    private val listener: HomeMainAdapter.UserActionListener?,
) : AbsHomeViewHolder<HomeViewData.Banner>(binding.root) {

    init {
        binding.viewPager.apply {
            adapter = HomePagerAdapter()
        }
        binding.indicator.attachTo(binding.viewPager)
    }

    override fun onBind(data: HomeViewData.Banner) {
        (binding.viewPager.adapter as? HomePagerAdapter)?.setDataList(data.bannerList)
    }


    private inner class HomePagerAdapter : RecyclerView.Adapter<HomePagerAdapter.HomeImageViewPagerHolder>() {

        private var imgUrlList = listOf<HomeViewData.Banner.BannerItem>()

        fun setDataList(dataList: List<HomeViewData.Banner.BannerItem>) {
            imgUrlList = dataList
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeImageViewPagerHolder {
            return HomeImageViewPagerHolder(HomePagerImageViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        override fun onBindViewHolder(holder: HomeImageViewPagerHolder, position: Int) {
            holder.onBind(imgUrlList.getOrNull(position))
        }

        override fun getItemCount() = imgUrlList.size

        private inner class HomeImageViewPagerHolder(private val binding: HomePagerImageViewBinding) : RecyclerView.ViewHolder(binding.root) {

            init {
                binding.imgBanner.apply {
                    setOnClickListener {
                        listener?.onClickBannerView(moveLink)
                    }
                }
            }

            private var moveLink: String? = null

            fun onBind(data: HomeViewData.Banner.BannerItem?) {
                moveLink = data?.link
                GlideUtil.instance.displayImage(GlideImageLoadData(binding.imgBanner, imageUrl = data?.imgUrl))
            }
        }
    }
}


class HomeTitleViewHolder(
    private val binding: HomeTitleItemViewBinding
) : AbsHomeViewHolder<HomeViewData.MainTitle>(binding.root) {

    override fun onBind(data: HomeViewData.MainTitle) {
        binding.setVariable(BR.viewData, data)
        binding.executePendingBindings()
    }
}


class HomeHorizontalViewHolder(
    private val binding: HomeListItemViewBinding,
    private val listener: HomeMainAdapter.UserActionListener?,
) : AbsHomeViewHolder<HomeViewData.SpecialPerfume>(binding.root) {

    init {
        binding.recyclerView.apply {
            layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply {
                layoutManager = this
            }

            adapter ?: HomeHorizontalAdapter().apply {
                onItemClick = { listener?.onClickPerfume(it) }
                adapter = this
            }

            if (itemDecorationCount == 0) {
                addItemDecoration(HomeHorizontalItemDecoration())
            }
        }
    }

    override fun onBind(data: HomeViewData.SpecialPerfume) {
        binding.txtTitle.text = data.title
        binding.btnMore.visibility = View.GONE
        (binding.recyclerView.adapter as? HomeHorizontalAdapter)?.setItemList(data.itemList)
    }
}


class HomeRecommendPerfumeViewHolder(
    private val binding: HomeListItemViewBinding,
    private val listener: HomeMainAdapter.UserActionListener?,
) : AbsHomeViewHolder<HomeViewData.RecommondPerfume>(binding.root) {

    init {
        binding.recyclerView.apply {
            isNestedScrollingEnabled = false

            layoutManager ?: GridLayoutManager(context, 2).apply {
                layoutManager = this
            }

            adapter ?: HomeRecommendPerfumeAdapter().apply {
                onItemClick = { listener?.onClickPerfume(it) }
                adapter = this
            }

            if (itemDecorationCount == 0) {
                addItemDecoration(HomeGridItemDecoration(spanCount = 2, spacing = 12.toDp(), horizontalMargin = 16.toDp()))
            }
        }
    }

    override fun onBind(data: HomeViewData.RecommondPerfume) {
        binding.txtTitle.text = data.title
        binding.btnMore.visibility = View.GONE
        (binding.recyclerView.adapter as? HomeRecommendPerfumeAdapter)?.setItemList(data.itemList)
    }
}


class HomeBrandViewHolder(
    private val binding: HomeListItemViewBinding,
    private val listener: HomeMainAdapter.UserActionListener?,
) : AbsHomeViewHolder<HomeViewData.Brand>(binding.root) {

    init {
        binding.apply {
            with(recyclerView) {
                isNestedScrollingEnabled = false
                layoutManager ?: GridLayoutManager(context, 2).apply {
                    layoutManager = this
                }

                adapter ?: HomeBrandAdapter().apply {
                    onItemClick = { listener?.onClickBrand(it) }
                    adapter = this
                }

                if (itemDecorationCount == 0) {
                    addItemDecoration(HomeGridItemDecoration(spanCount = 2, spacing = 12.toDp(), horizontalMargin = 16.toDp()))
                }
            }

            btnMore.setOnClickListener {
                listener?.onClickMore(HomeViewType.HOME_BRAND_TYPE)
            }
        }
    }

    override fun onBind(data: HomeViewData.Brand) {
        binding.txtTitle.text = data.title
        binding.btnMore.visibility = View.VISIBLE
        (binding.recyclerView.adapter as? HomeBrandAdapter)?.setItemList(data.itemList)
    }
}


class HomeAccordViewHolder(
    private val binding: HomeListItemViewBinding,
    private val listener: HomeMainAdapter.UserActionListener?,
) : AbsHomeViewHolder<HomeViewData.Accord>(binding.root) {

    init {
        binding.apply {
            with(recyclerView) {
                isNestedScrollingEnabled = false
                layoutManager ?: GridLayoutManager(context, 3).apply {
                    layoutManager = this
                }

                adapter ?: HomeAccordAdapter().apply {
                    onItemClick = { listener?.onClickAccord(it) }
                    adapter = this
                }

                if (itemDecorationCount == 0) {
                    addItemDecoration(HomeGridItemDecoration(spanCount = 3, spacing = 12.toDp(), horizontalMargin = 16.toDp()))
                }
            }

            btnMore.setOnClickListener {
                listener?.onClickMore(HomeViewType.HOME_ACCORD_TYPE)
            }
        }
    }

    override fun onBind(data: HomeViewData.Accord) {
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