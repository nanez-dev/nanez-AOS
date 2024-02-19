package com.nane.login.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.login.R
import com.nane.login.databinding.LoginFragmentBinding
import com.nane.login.presentation.data.LoginActEventData
import com.nane.login.presentation.viewmodel.LoginActViewModel
import com.nane.login.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by iseungjun on 2023/10/14
 */
@AndroidEntryPoint
class LoginFragment : BaseBindFragment<LoginFragmentBinding, LoginViewModel>(R.layout.login_fragment) {

    private val actViewModel: LoginActViewModel by activityViewModels()

    override fun createViewModel() = viewModels<LoginViewModel>().value


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isBackPressedInterceptor = true
    }

    override fun initFragment(dataBinding: LoginFragmentBinding, viewModel: LoginViewModel) {
        dataBinding.apply {
            vgGuest.setOnClickListener {
                actViewModel.onEventAction(LoginActEventData.StartGuestMode)
            }

            vgEmail.setOnClickListener {
                actViewModel.onEventAction(LoginActEventData.MoveEmailLogin)
            }
        }
    }

    override fun onBackPressedInterceptor(): Boolean {
        if (dataBinding?.vgLoginMainGroup?.visibility != View.VISIBLE) {
            dataBinding?.vgLoginMainGroup?.visibility = View.VISIBLE
        } else {
            isBackPressedInterceptor = false
        }
        return super.onBackPressedInterceptor()
    }
}