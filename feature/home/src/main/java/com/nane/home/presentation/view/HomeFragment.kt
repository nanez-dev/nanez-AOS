package com.nane.home.presentation.view

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.ActionbarView
import com.nane.base.view.BaseBindFragment
import com.nane.home.R
import com.nane.home.databinding.HomeFragmentBinding
import com.nane.home.presentation.viewmodel.HomeViewModel
import com.nane.home.presentation.view.adapter.HomeMainAdapter
import com.nane.home.presentation.view.adapter.decoration.HomeMainItemDecoration
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
                    override fun onClickBack() {}
                    override fun onClickRight() {
                        //임시 로그인 이동 페이지
                        activity?.let {
//                            startActivity(LoginActivity.createIntent(it))
                        }
                    }
                }
            }

            with(homeRecyclerView) {
                adapter ?: HomeMainAdapter().apply { adapter = this }
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

}