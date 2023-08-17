package org.techtown.nanez

import androidx.fragment.app.viewModels
import org.techtown.nanez.base.BaseBindFragment
import org.techtown.nanez.base.BaseViewModel
import org.techtown.nanez.databinding.HomeFragmentBinding

class HomeFragment : BaseBindFragment<HomeFragmentBinding, BaseViewModel>(R.layout.home_fragment) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: HomeFragmentBinding, viewModel: BaseViewModel) {

    }

}