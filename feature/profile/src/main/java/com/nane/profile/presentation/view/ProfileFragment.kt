package com.nane.profile.presentation.view

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.login.presentation.view.LoginActivity
import com.nane.password.presentation.view.change.PasswordChangeActivity
import com.nane.profile.R
import com.nane.profile.databinding.ProfileFragmentBinding
import com.nane.profile.presentation.viewmodel.ProfileViewModel
import com.nane.setting.presentation.view.RegistrationActivity
import com.nane.setting.presentation.view.SettingActivity
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.session.SessionManager

@AndroidEntryPoint
class ProfileFragment : BaseBindFragment<ProfileFragmentBinding, ProfileViewModel>(R.layout.profile_fragment) {

    override fun createViewModel() = viewModels<ProfileViewModel>().value

    override fun initFragment(dataBinding: ProfileFragmentBinding, viewModel: ProfileViewModel) {
        dataBinding.run {
            actionBar.setTitle(getString(com.nane.base.R.string.label_profile))
            initHelpCenterView(this)
        }

        viewModel.profileViewData.observe(viewLifecycleOwner) { data ->
            dataBinding.profileLoginView.setViewData(data)
        }
    }

    override fun onResume() {
        super.onResume()
        dataBinding?.let {
            val isLogin = SessionManager.instance.isLoginCheck()
            setVisibleMenu(isLogin)
            if (isLogin) {
                it.profileLoginView.visibility = View.VISIBLE
                it.profileNotLoginView.visibility = View.GONE

                viewModel?.getProfileInfo()
            } else {
                it.profileNotLoginView.visibility = View.VISIBLE
                it.profileLoginView.visibility = View.GONE

                initNotLoginView(it.profileNotLoginView)
            }
        }
    }

    private fun initNotLoginView(notLoginView: ProfileNoLoginView) {
        notLoginView.run {
            setUserActionListener(object : ProfileNoLoginView.UserActionListener {
                override fun onClickLogin() {
                    if (!isAdded) {
                        return
                    }

                    activity?.let {
                        startActivity(LoginActivity.createIntent(it))
                    }
                }
            })
        }
    }


    private fun initHelpCenterView(dataBinding: ProfileFragmentBinding) {
        dataBinding.run {
            btnNotice.setOnClickListener {
                if (!isAdded) {
                    return@setOnClickListener
                }

            }

            btnPrivacyPolicy.setOnClickListener {
                if (!isAdded) {
                    return@setOnClickListener
                }
                moveToUrl(getString(com.nane.base.R.string.nane_privacy_policy_url))
            }

            btnReport.setOnClickListener {
                if (!isAdded) {
                    return@setOnClickListener
                }

            }

            btnTermsOfUse.setOnClickListener {
                if (!isAdded) {
                    return@setOnClickListener
                }
                moveToUrl(getString(com.nane.base.R.string.nane_service_terms_of_use_url))
            }

            btnProductRegistrationRequest.setOnClickListener {
                if (!isAdded) {
                    return@setOnClickListener
                }

            }
            btnChangePassword.setOnClickListener {
                if (!isAdded) {
                    return@setOnClickListener
                }

                activity?.let {
                    it.startActivity(PasswordChangeActivity.createIntent(it))
                }
            }

            btnSetting.setOnClickListener {
                if (!isAdded) {
                    return@setOnClickListener
                }

                activity?.let {
                    it.startActivity(SettingActivity.createIntent(it))
                }
            }

            btnProductRegistrationRequest.setOnClickListener {
                if (!isAdded) {
                    return@setOnClickListener
                }

                activity?.let {
                    it.startActivity(RegistrationActivity.createIntent(it))
                }
            }
        }
    }

    private fun setVisibleMenu(isLogin: Boolean) {
        dataBinding?.vgPasswordChange?.visibility = if (isLogin) View.VISIBLE else View.GONE
        dataBinding?.vgSetting?.visibility = if (isLogin) View.VISIBLE else View.GONE
    }

    private fun moveToUrl(url: String?) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        activity?.startActivity(intent)
    }
}
