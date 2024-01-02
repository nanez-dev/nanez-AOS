package org.techtown.nanez

import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import org.techtown.nanez.databinding.NoLoginProfileFragmentBinding

class ProfileFragment : BaseBindFragment<NoLoginProfileFragmentBinding, BaseViewModel>(R.layout.no_login_profile_fragment) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: NoLoginProfileFragmentBinding, viewModel: BaseViewModel) {

    }
}
