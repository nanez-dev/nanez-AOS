package com.nane.theme.presentation.view

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.BaseBindFragment
import com.nane.theme.R
import com.nane.theme.databinding.ThemeBrandActivityBinding
import com.nane.theme.presentation.view.adapter.AllBrandsAdapter
import com.nane.theme.presentation.view.adapter.PopularBrandsAdapter
import com.nane.theme.presentation.view.adapter.decoration.AllBrandItemDecoration
import com.nane.theme.presentation.view.adapter.decoration.PopularBrandItemDecoration
import com.nane.theme.presentation.viewmodel.ThemeBrandViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.addFragment

@AndroidEntryPoint
class ThemeBrandActivity : BaseBindActivity<ThemeBrandActivityBinding, ThemeBrandViewModel>(R.layout.theme_brand_activity) {

    private var isDirectlyNavigatedToDetail = false

    override fun createViewModel(): ThemeBrandViewModel = viewModels<ThemeBrandViewModel>().value

    // 상세 화면으로 바로 이동한 경우는 뒤로가기 시 액티비티 종료
    override fun onActionBackPressed() {
        supportFragmentManager.also {
            val fragment = it.findFragmentById(dataBinding?.container?.id ?: 0) as? BaseBindFragment<*, *>
            if (isDirectlyNavigatedToDetail || fragment == null) {
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

            with(rvPopularBrands) {
                adapter ?: PopularBrandsAdapter().apply { adapter = this }
                layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply { layoutManager = this }
                if (itemDecorationCount == 0) addItemDecoration(PopularBrandItemDecoration())
                (adapter as PopularBrandsAdapter).setOnItemClickListener(
                    object: PopularBrandsAdapter.ItemClickListener {
                        override fun onItemClick(idx: Int) {
                            addFragment(dataBinding.container, tag = "ThemeBrandDetailFragment", arguments = ThemeBrandDetailFragment.createArgument(idx)) {
                                ThemeBrandDetailFragment()
                            }
                            setDetailFragmentVisibility(true)
                        }
                    }
                )
            }

            with(rvAllBrands) {
                adapter ?: AllBrandsAdapter().apply { adapter = this }
                layoutManager ?: GridLayoutManager(context, 2).apply { layoutManager = this }
                if (itemDecorationCount == 0) addItemDecoration(AllBrandItemDecoration())
                (adapter as AllBrandsAdapter).setOnItemClickListener(
                    object: AllBrandsAdapter.ItemClickListener {
                        override fun onItemClick(idx: Int) {
                            addFragment(dataBinding.container, tag = "ThemeBrandDetailFragment", arguments = ThemeBrandDetailFragment.createArgument(idx)) {
                                ThemeBrandDetailFragment()
                            }
                            setDetailFragmentVisibility(true)
                        }
                    }
                )
            }
        }
        
        viewModel.popularBrandsViewDataList.observe(this) {
            (dataBinding.rvPopularBrands.adapter as? PopularBrandsAdapter)?.setItemList(it)
        }

        viewModel.allBrandsViewDataList.observe(this) {
            (dataBinding.rvAllBrands.adapter as? AllBrandsAdapter)?.setItemList(it)
        }

        viewModel.getBrandViewData()

        val brandId = intent?.getIntExtra(ThemeBrandDetailFragment.BRAND_ID, -1) ?: -1
        if (brandId > 0) {
            isDirectlyNavigatedToDetail = true
            setDetailFragmentVisibility(true)
            addFragment(dataBinding.container, tag = "ThemeBrandDetailFragment", arguments = ThemeBrandDetailFragment.createArgument(brandId)) {
                ThemeBrandDetailFragment()
            }
        } else {
            isDirectlyNavigatedToDetail = false
            setDetailFragmentVisibility(false)
            viewModel.getBrandViewData()
        }
    }

    private fun setDetailFragmentVisibility(isVisible: Boolean) {
        if (isVisible) {
            dataBinding?.container?.visibility = View.VISIBLE
            dataBinding?.vgMain?.visibility = View.GONE
        } else {
            dataBinding?.container?.visibility = View.GONE
            dataBinding?.vgMain?.visibility = View.VISIBLE
        }
    }
}