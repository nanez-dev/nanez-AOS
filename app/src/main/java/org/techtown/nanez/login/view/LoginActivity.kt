package org.techtown.nanez.login.view

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.R
import org.techtown.nanez.base.BaseBindActivity
import org.techtown.nanez.databinding.LoginActivityBinding
import org.techtown.nanez.join.JoinActivity
import org.techtown.nanez.login.data.LoginEventData
import org.techtown.nanez.login.viewmodel.LoginViewModel
import org.techtown.nanez.utils.util.eventObserve

@AndroidEntryPoint
class LoginActivity : BaseBindActivity<LoginActivityBinding, LoginViewModel>(R.layout.login_activity) {

    override fun createViewModel() = viewModels<LoginViewModel>().value

    override fun initActivity(dataBinding: LoginActivityBinding, viewModel: LoginViewModel) {
        dataBinding.apply {
            btnLogin.setOnClickListener {
                viewModel.requestLogin(editEmail.text?.toString(), editPassword.text?.toString())
            }

            btnJoin.setOnClickListener {
                startActivity(Intent(this@LoginActivity, JoinActivity::class.java))
            }
        }

        viewModel.eventData.eventObserve(this) { event ->
            when (event) {
                is LoginEventData.LoginSuccess -> {
                    finish()
                }
                is LoginEventData.NotMatchEmail -> {

                }
                is LoginEventData.NotMatchPassword -> {

                }
                is LoginEventData.NotInputEmail -> {

                }
                is LoginEventData.NotInputPassword -> {

                }
            }
        }
    }


    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
        }
    }
}