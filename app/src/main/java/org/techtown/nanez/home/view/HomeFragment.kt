package org.techtown.nanez.home.view

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.R
import org.techtown.nanez.base.BaseBindFragment
import org.techtown.nanez.common.ActionbarView
import org.techtown.nanez.databinding.HomeFragmentBinding
import org.techtown.nanez.home.view.adapter.HomeMainAdapter
import org.techtown.nanez.home.view.adapter.decoration.HomeMainItemDecoration
import org.techtown.nanez.home.viewmodel.HomeViewModel
import org.techtown.nanez.login.view.LoginActivity

@AndroidEntryPoint
class HomeFragment : BaseBindFragment<HomeFragmentBinding, HomeViewModel>(R.layout.home_fragment) {

    override fun createViewModel() = viewModels<HomeViewModel>().value

    override fun initFragment(dataBinding: HomeFragmentBinding, viewModel: HomeViewModel) {
        dataBinding.apply {
            with(actionBar) {
                setHomeImg(true)
                setRightBtn(R.drawable.img_pencel)
                actionListener = object : ActionbarView.ActionListener {
                    override fun onClickBack() {}
                    override fun onClickRight() {
                        //임시 로그인 이동 페이지
                        activity?.let {
                            startActivity(LoginActivity.createIntent(it))
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