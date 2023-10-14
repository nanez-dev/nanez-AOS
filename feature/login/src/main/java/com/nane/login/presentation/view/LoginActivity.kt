package com.nane.login.presentation.view

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.base.viewmodel.BaseViewModel
import com.nane.login.R
import com.nane.login.databinding.LoginActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.addFragment

/**
 * Created by iseungjun on 2023/10/14
 */
@AndroidEntryPoint
class LoginActivity : BaseBindActivity<LoginActivityBinding, BaseViewModel>(R.layout.login_activity) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initActivity(dataBinding: LoginActivityBinding, viewModel: BaseViewModel) {
        addFragment(dataBinding.container, tag = "LoginFragment") {
            LoginFragment()
        }
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }
}