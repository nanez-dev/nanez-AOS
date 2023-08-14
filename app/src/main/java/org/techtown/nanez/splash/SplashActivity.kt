package org.techtown.nanez.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import org.techtown.nanez.login.LoginActivity
import org.techtown.nanez.main.MainActivity
import org.techtown.nanez.R
import org.techtown.nanez.databinding.SplashActivityBinding
import org.techtown.nanez.base.BaseBindActivity
import org.techtown.nanez.splash.viewmodel.SplashViewModel

class SplashActivity : BaseBindActivity<SplashActivityBinding, SplashViewModel>(R.layout.splash_activity) {

    override fun createViewModel() = viewModels<SplashViewModel>().value

    override fun initActivity(dataBinding: SplashActivityBinding, viewModel: SplashViewModel) {
        
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("accessToken", "empty")
        when (token) {
            "empty" -> {
                Log.d("태그", "로그인 페이지 이동")
                startActivity(Intent(this, LoginActivity::class.java))
            }

            else -> {
                Log.d("태그", "자동로그인 완료")
                startActivity(Intent(this, MainActivity::class.java))


            }
        }
    }
}