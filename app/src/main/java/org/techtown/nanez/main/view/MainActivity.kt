package org.techtown.nanez.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.dialog.CommonTextDialog
import com.nane.base.view.dialog.data.DialogBuildData
import com.nane.home.presentation.view.HomeFragment
import com.nane.login.presentation.view.LoginActivity
import com.nane.profile.presentation.view.ProfileFragment
import com.nane.storage.presentation.view.StorageFragment
import com.nane.theme.presentation.view.ThemeAccordActivity
import com.nane.theme.presentation.view.ThemeBrandActivity
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.R
import org.techtown.nanez.databinding.MainActivityBinding
import org.techtown.nanez.main.viewmodel.MainViewModel
import org.techtown.nanez.utils.session.SessionManager
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.addFragment

@AndroidEntryPoint
class MainActivity : BaseBindActivity<MainActivityBinding, MainViewModel>(R.layout.main_activity) {

    private var waitTime = 0L

    override fun createViewModel() = viewModels<MainViewModel>().value

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
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
                        if (!SessionManager.instance.isLoginCheck()) {
                            CommonTextDialog().apply {
                                setDialogData(
                                    DialogBuildData(
                                        title = ResUtils.instance.getString(com.nane.base.R.string.msg_need_login),
                                        positiveText = ResUtils.instance.getString(com.nane.base.R.string.label_login),
                                        onPositiveAction = {
                                            startActivity(LoginActivity.createIntent(this@MainActivity))
                                        }
                                    )
                                )
                            }.show(this@MainActivity)

                            return@setOnItemSelectedListener false
                        }
                        addFragment(frameLayout, tag = "StorageFragment") {
                            StorageFragment()
                        }
                    }
                }
                true
            }
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


    override fun onActionBackPressed() {
        if(System.currentTimeMillis() - waitTime >=1500 ) {
            waitTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }

}
