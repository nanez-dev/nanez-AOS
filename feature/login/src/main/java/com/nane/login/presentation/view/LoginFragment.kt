package com.nane.login.presentation.view

import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.login.R
import com.nane.login.databinding.LoginFragmentBinding
import com.nane.login.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by iseungjun on 2023/10/14
 */
@AndroidEntryPoint
class LoginFragment : BaseBindFragment<LoginFragmentBinding, LoginViewModel>(R.layout.login_fragment) {

    override fun createViewModel() = viewModels<LoginViewModel>().value

    override fun initFragment(dataBinding: LoginFragmentBinding, viewModel: LoginViewModel) {
        dataBinding.apply {
            vgGuest.setOnClickListener {
                activity?.let {

                }
            }
        }
    }
}