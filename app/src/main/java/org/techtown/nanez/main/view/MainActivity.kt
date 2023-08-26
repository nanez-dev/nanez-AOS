package org.techtown.nanez.main.view

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.home.view.HomeFragment
import org.techtown.nanez.ProfileFragment
import org.techtown.nanez.R
import org.techtown.nanez.StorageFragment
import org.techtown.nanez.base.BaseBindActivity
import org.techtown.nanez.databinding.MainActivityBinding
import org.techtown.nanez.main.viewmodel.MainViewModel
import org.techtown.nanez.utils.util.addFragment

@AndroidEntryPoint
class MainActivity : BaseBindActivity<MainActivityBinding, MainViewModel>(R.layout.main_activity) {

    override fun createViewModel() = viewModels<MainViewModel>().value

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    override fun initActivity(dataBinding: MainActivityBinding, viewModel: MainViewModel) {
        dataBinding.apply {

            addFragment(frameLayout, tag = "HomeFragment") {
                HomeFragment()
            }

            bottomNavView.setOnItemSelectedListener { menu ->
                when (menu.itemId) {
                    R.id.menuHome -> {
                        addFragment(frameLayout, tag = "HomeFragment") {
                            HomeFragment()
                        }
                    }
                    R.id.menuMypage -> {
                        addFragment(frameLayout, tag = "ProfileFragment") {
                            ProfileFragment()
                        }
                    }
                    R.id.menuStorage -> {
                        addFragment(frameLayout, tag = "StorageFragment") {
                            StorageFragment()
                        }
                    }
                }
                true
            }
            bottomNavView.itemIconTintList = null
        }

        viewModel.apply {
            val content: View = findViewById(android.R.id.content)
            content.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        return if (viewModel.isLoginCheckDone) {
                            content.viewTreeObserver.removeOnPreDrawListener(this)
                            true
                        } else {
                            false
                        }
                    }
                }
            )
        }

        viewModel.checkAutoLogin()
    }

}
