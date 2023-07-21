package org.techtown.nanez

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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