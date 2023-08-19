package org.techtown.nanez.home.view

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import org.techtown.nanez.R
import org.techtown.nanez.base.BaseBindFragment
import org.techtown.nanez.base.BaseViewModel
import org.techtown.nanez.common.ActionbarView
import org.techtown.nanez.databinding.HomeFragmentBinding
import org.techtown.nanez.home.viewmodel.HomeViewModel

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

                    }
                }
            }

            with(homeRecyclerView) {
                adapter = HomeMainAdapter()
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

}