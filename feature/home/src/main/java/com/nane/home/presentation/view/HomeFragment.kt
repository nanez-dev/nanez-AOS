package com.nane.home.presentation.view

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.ActionbarView
import com.nane.base.view.BaseBindFragment
import com.nane.home.R
import com.nane.home.databinding.HomeFragmentBinding
import com.nane.home.presentation.data.HomeViewType
import com.nane.home.presentation.data.PerfumeItemViewData
import com.nane.home.presentation.view.adapter.HomeMainAdapter
import com.nane.home.presentation.view.adapter.decoration.HomeMainItemDecoration
import com.nane.home.presentation.viewmodel.HomeViewModel
import com.nane.theme.presentation.view.ThemeAccordActivity
import com.nane.theme.presentation.view.ThemeBrandActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseBindFragment<HomeFragmentBinding, HomeViewModel>(R.layout.home_fragment) {

    override fun createViewModel() = viewModels<HomeViewModel>().value

    override fun initFragment(dataBinding: HomeFragmentBinding, viewModel: HomeViewModel) {
        dataBinding.apply {
            with(actionBar) {
                setHomeImg(true)
                setRightBtn(com.nane.base.R.drawable.icon_search)
                actionListener = object : ActionbarView.ActionListener {
                    override fun onClickRight() {

                    }
                }
            }

            with(homeRecyclerView) {
                adapter ?: HomeMainAdapter().apply {
                    listener = object : HomeMainAdapter.UserActionListener {
                        override fun onClickAccord(accordId: Int) {
                            moveToAccord(accordId)
                        }

                        override fun onClickBrand(brandId: Int) {
                            moveToBrand(brandId)
                        }

                        override fun onClickBannerView(moveToUrl: String?) {

                        }

                        override fun onClickPerfume(data: PerfumeItemViewData?) {

                        }

                        override fun onClickMore(@HomeViewType moreType: Int) {
                            when (moreType) {
                                HomeViewType.HOME_BRAND_TYPE -> {
                                    moveToBrand(0)
                                }
                                HomeViewType.HOME_ACCORD_TYPE -> {
                                    moveToAccord(0)
                                }
                            }
                        }
                    }
                    adapter = this
                }
                layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false).apply { layoutManager = this }

                if (itemDecorationCount == 0) {
                    addItemDecoration(HomeMainItemDecoration())
                }
            }
        }


        viewModel.viewDataList.observe(viewLifecycleOwner) {
            (dataBinding.homeRecyclerView.adapter as? HomeMainAdapter)?.setDataList(it)
        }

        viewModel.getMainData()
    }

    private fun moveToAccord(accordId: Int) {
        activity?.let {
            it.startActivity(ThemeAccordActivity.createIntent(it, accordId))
        }
    }

    private fun moveToBrand(brandId: Int) {
        activity?.let {
            it.startActivity(ThemeBrandActivity.createIntent(it, brandId))
        }
    }
}