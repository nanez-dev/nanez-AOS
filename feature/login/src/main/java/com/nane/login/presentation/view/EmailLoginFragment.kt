package com.nane.login.presentation.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.login.R
import com.nane.login.databinding.EmailLoginFragmentBinding
import com.nane.login.presentation.data.EmailLoginEventData
import com.nane.login.presentation.viewmodel.EmailLoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.eventObserve

@AndroidEntryPoint
class EmailLoginFragment : BaseBindFragment<EmailLoginFragmentBinding, EmailLoginViewModel>(R.layout.email_login_fragment) {

    override fun createViewModel() = viewModels<EmailLoginViewModel>().value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initFragment(dataBinding: EmailLoginFragmentBinding, viewModel: EmailLoginViewModel) {
        dataBinding.apply {
            btnLogin.setOnClickListener {
                viewModel.requestLogin(editEmail.text?.toString(), editPassword.text?.toString())
            }

            btnJoin.setOnClickListener {
//                startActivity(Intent(this@EmailLoginActivity, JoinActivity::class.java))
            }
        }

        viewModel.eventData.eventObserve(this) { event ->
            when (event) {
                is EmailLoginEventData.LoginSuccess -> {

                }
                is EmailLoginEventData.NotMatchLoginInfo -> {

                }
                is EmailLoginEventData.NotInputEmail -> {

                }
                is EmailLoginEventData.NotInputPassword -> {

                }
            }
        }
    }
}