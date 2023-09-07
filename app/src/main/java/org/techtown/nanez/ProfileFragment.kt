package org.techtown.nanez

import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import org.techtown.nanez.databinding.ProfileFragmentBinding

class ProfileFragment : BaseBindFragment<ProfileFragmentBinding, BaseViewModel>(R.layout.profile_fragment) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: ProfileFragmentBinding, viewModel: BaseViewModel) {

    }
}
