package com.nane.theme.presentation.view

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.BaseBindFragment
import com.nane.theme.R
import com.nane.theme.databinding.ThemeBrandActivityBinding
import com.nane.theme.presentation.data.BrandViewType
import com.nane.theme.presentation.view.adapter.BrandAdapter
import com.nane.theme.presentation.view.adapter.decoration.BrandItemDecoration
import com.nane.theme.presentation.viewmodel.ThemeBrandViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.addFragment

@AndroidEntryPoint
class ThemeBrandActivity : BaseBindActivity<ThemeBrandActivityBinding, ThemeBrandViewModel>(R.layout.theme_brand_activity) {

    private var hasBeenDirectlyNavigatedToDetail = false

    override fun createViewModel(): ThemeBrandViewModel = viewModels<ThemeBrandViewModel>().value

    // 상세 화면으로 바로 이동한 경우는 뒤로가기 시 액티비티 종료
    override fun onActionBackPressed() {
        supportFragmentManager.also {
            val fragment = it.findFragmentById(dataBinding?.container?.id ?: 0) as? BaseBindFragment<*, *>
            if (hasBeenDirectlyNavigatedToDetail || fragment == null) {
                finish()
            } else {
                it.popBackStack()
                it.beginTransaction().remove(fragment).commit()
                setDetailFragmentVisibility(false)
            }
        }
    }

    override fun initActivity(dataBinding: ThemeBrandActivityBinding, viewModel: ThemeBrandViewModel) {
        dataBinding.apply {
            with(actionBar) {
                setTitle(getString(com.nane.base.R.string.label_theme_brand_title))
                setUseBackBtn { onActionBackPressed() }
            }

            with(rvBrandItems) {
                adapter ?: BrandAdapter().apply { adapter = this }
                layoutManager ?: GridLayoutManager(context, 2).apply {
                    spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return when ((adapter as BrandAdapter).getItemViewType(position)) {
                                BrandViewType.ALL_BRAND_ITEM_TYPE -> 1
                                else -> 2
                            }
                        }
                    }
                    layoutManager = this
                }
                if (itemDecorationCount == 0) addItemDecoration(BrandItemDecoration())
                (adapter as BrandAdapter).setOnBrandItemClickListener(
                    object: BrandAdapter.BrandItemClickListener {

                        override fun onPopularBrandItemClick(itemId: Int) {
                            addFragment(dataBinding.container, tag = TAG_FRAGMENT, arguments = ThemeBrandDetailFragment.createArgument(itemId)) {
                                ThemeBrandDetailFragment()
                            }
                            setDetailFragmentVisibility(true)
                        }

                        override fun onAllBrandItemClick(itemId: Int) {
                            addFragment(dataBinding.container, tag = TAG_FRAGMENT, arguments = ThemeBrandDetailFragment.createArgument(itemId)) {
                                ThemeBrandDetailFragment()
                            }
                            setDetailFragmentVisibility(true)
                        }
                    }
                )
            }
        }

        viewModel.brandItemViewDataList.observe(this) {
            (dataBinding.rvBrandItems.adapter as? BrandAdapter)?.setItemList(it)
        }

        viewModel.getBrandViewData()

        val brandId = intent?.getIntExtra(ThemeBrandDetailFragment.BRAND_ID, -1) ?: -1
        if (brandId > 0) {
            hasBeenDirectlyNavigatedToDetail = true
            setDetailFragmentVisibility(true)
            addFragment(dataBinding.container, tag = TAG_FRAGMENT, arguments = ThemeBrandDetailFragment.createArgument(brandId)) {
                ThemeBrandDetailFragment()
            }
        } else {
            hasBeenDirectlyNavigatedToDetail = false
            setDetailFragmentVisibility(false)
            viewModel.getBrandViewData()
        }
    }

    private fun setDetailFragmentVisibility(isVisible: Boolean) {
        if (isVisible) {
            dataBinding?.container?.visibility = View.VISIBLE
            dataBinding?.rvBrandItems?.visibility = View.GONE
        } else {
            dataBinding?.container?.visibility = View.GONE
            dataBinding?.rvBrandItems?.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val TAG_FRAGMENT ="ThemeBrandDetailFragment"

        fun createIntent(context: Context, brandId: Int): Intent {
            return Intent(context, ThemeBrandActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                putExtra(ThemeBrandDetailFragment.BRAND_ID, brandId)
            }
        }
    }
}