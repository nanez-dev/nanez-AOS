package com.nane.login.presentation.view

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.BaseBindFragment
import com.nane.login.R
import com.nane.login.databinding.LoginActivityBinding
import com.nane.login.presentation.data.LoginActEventData
import com.nane.login.presentation.viewmodel.LoginActViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.addFragment
import org.techtown.nanez.utils.util.eventObserve

/**
 * Created by iseungjun on 2023/10/14
 */
@AndroidEntryPoint
class LoginActivity : BaseBindActivity<LoginActivityBinding, LoginActViewModel>(R.layout.login_activity) {

    override fun createViewModel() = viewModels<LoginActViewModel>().value

    override fun initActivity(dataBinding: LoginActivityBinding, viewModel: LoginActViewModel) {
        addFragment(dataBinding.container, tag = "LoginFragment") {
            LoginFragment()
        }

        viewModel.eventData.eventObserve(this) {
            when (it) {
                is LoginActEventData.StartGuestMode -> {
                    finish()
                }

                is LoginActEventData.MoveEmailLogin -> {
                    addFragment(dataBinding.container, tag = "EmailLoginFragment") {
                        EmailLoginFragment()
                    }
                }
            }
        }
    }


    override fun onActionBackPressed() {
        supportFragmentManager.also {
            val fragment = it.findFragmentById(dataBinding?.container?.id ?: 0) as? BaseBindFragment<*, *>
            val isFragmentInterceptor = fragment?.onBackPressedInterceptor() ?: false
            if (!isFragmentInterceptor) { // fragment에서 interceptor하지 않았을때만 super.onBackPressed를 하도록 한다.
                if (it.backStackEntryCount > 1) {
                    onBackPressedDispatcher.onBackPressed()
                } else {
                    finish()
                }
            }
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