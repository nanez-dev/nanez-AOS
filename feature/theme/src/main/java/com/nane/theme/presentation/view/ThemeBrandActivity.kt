package com.nane.theme.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindActivity
import com.nane.theme.R
import com.nane.theme.databinding.ThemeBrandActivityBinding
import com.nane.theme.presentation.view.adapter.AllBrandsAdapter
import com.nane.theme.presentation.view.adapter.PopularBrandsAdapter
import com.nane.theme.presentation.view.adapter.decoration.AllBrandItemDecoration
import com.nane.theme.presentation.view.adapter.decoration.PopularBrandItemDecoration
import com.nane.theme.presentation.viewmodel.ThemeBrandViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeBrandActivity : BaseBindActivity<ThemeBrandActivityBinding, ThemeBrandViewModel>(R.layout.theme_brand_activity) {

    private val brandDetailFragment = ThemeBrandDetailFragment()

    override fun createViewModel(): ThemeBrandViewModel = viewModels<ThemeBrandViewModel>().value

    override fun onActionBackPressed() {

    }

    override fun initActivity(dataBinding: ThemeBrandActivityBinding, viewModel: ThemeBrandViewModel) {
        dataBinding.apply {
            with(actionBar) {
                setTitle(getString(com.nane.base.R.string.label_theme_brand_title))
                setUseBackBtn {
//                     parentFragmentManager
//                         .beginTransaction()
//                         .replace()
//                         .commit()
                }
            }

            with(rvPopularBrands) {
                adapter ?: PopularBrandsAdapter().apply { adapter = this }
                layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply { layoutManager = this }
                if (itemDecorationCount == 0) addItemDecoration(PopularBrandItemDecoration())
            }

            with(rvAllBrands) {
                adapter ?: AllBrandsAdapter().apply { adapter = this }
                layoutManager ?: GridLayoutManager(context, 2).apply { layoutManager = this }
                if (itemDecorationCount == 0) addItemDecoration(AllBrandItemDecoration())
                (adapter as AllBrandsAdapter).setOnItemClickListener(
                    object: AllBrandsAdapter.ItemClickListener {
                        override fun onItemClick(idx: Int) {
                            val args = Bundle()
                            args.putInt(ThemeBrandDetailFragment.BRAND_ID, idx)
                            brandDetailFragment.arguments = args
                            supportFragmentManager
                                .beginTransaction()
                                .replace(android.R.id.content, brandDetailFragment)
                                .commit()
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

        viewModel.getPopularBrandViewData()
        viewModel.getAllBrandViewData()
    }
}