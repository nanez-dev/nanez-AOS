package com.nane.theme.presentation.view

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.theme.R
import com.nane.theme.databinding.ThemeBrandFragmentBinding
import com.nane.theme.presentation.view.adapter.AllBrandsAdapter
import com.nane.theme.presentation.view.adapter.PopularBrandsAdapter
import com.nane.theme.presentation.view.adapter.decoration.AllBrandItemDecoration
import com.nane.theme.presentation.view.adapter.decoration.PopularBrandItemDecoration
import com.nane.theme.presentation.viewmodel.ThemeBrandViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeBrandFragment : BaseBindFragment<ThemeBrandFragmentBinding, ThemeBrandViewModel>(R.layout.theme_brand_fragment) {

    override fun createViewModel(): ThemeBrandViewModel = viewModels<ThemeBrandViewModel>().value

    override fun initFragment(dataBinding: ThemeBrandFragmentBinding, viewModel: ThemeBrandViewModel) {

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
                adapter ?: AllBrandsAdapter {}.apply { adapter = this }
                layoutManager ?: GridLayoutManager(context, 2).apply { layoutManager = this }
                if (itemDecorationCount == 0) addItemDecoration(AllBrandItemDecoration())
            }
        }
        
        viewModel.popularBrandsViewDataList.observe(viewLifecycleOwner) {
            (dataBinding.rvPopularBrands.adapter as? PopularBrandsAdapter)?.setItemList(it)
        }

        viewModel.allBrandsViewDataList.observe(viewLifecycleOwner) {
            (dataBinding.rvAllBrands.adapter as? AllBrandsAdapter)?.setItemList(it)
        }

        viewModel.getPopularBrandViewData()
        viewModel.getAllBrandViewData()
    }
}