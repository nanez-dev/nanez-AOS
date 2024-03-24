package com.nane.profile.presentation.view.password

import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import com.nane.login.presentation.view.LoginActivity
import com.nane.profile.R
import com.nane.profile.databinding.PasswordChangeCompleteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by haul on 3/24/24
 */
@AndroidEntryPoint
class PasswordChangeCompleteFragment : BaseBindFragment<PasswordChangeCompleteFragmentBinding, BaseViewModel>(R.layout.password_change_complete_fragment) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: PasswordChangeCompleteFragmentBinding, viewModel: BaseViewModel) {
        dataBinding.btnLogin.setOnClickListener {
            activity?.let {
                it.startActivity(LoginActivity.createIntent(it, isAllClear = true))
                it.finish()
            }
        }
    }
}