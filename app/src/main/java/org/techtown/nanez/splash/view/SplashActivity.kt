package org.techtown.nanez.splash.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.R
import org.techtown.nanez.base.BaseBindActivity
import org.techtown.nanez.common.eventObserve
import org.techtown.nanez.databinding.SplashActivityBinding
import org.techtown.nanez.login.LoginActivity
import org.techtown.nanez.main.MainActivity
import org.techtown.nanez.splash.data.SplashEventData
import org.techtown.nanez.splash.viewmodel.SplashViewModel

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseBindActivity<SplashActivityBinding, SplashViewModel>(R.layout.splash_activity) {

    override fun createViewModel() = viewModels<SplashViewModel>().value

    override fun initActivity(dataBinding: SplashActivityBinding, viewModel: SplashViewModel) {

        viewModel.eventData.eventObserve(this) { event ->
            when (event) {
                is SplashEventData.MoveToMainPage -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                is SplashEventData.MoveToLoginPage -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }

        viewModel.checkAutoLogin()
    }
}