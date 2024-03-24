package com.nane.profile.presentation.view

import android.view.View
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.login.presentation.view.LoginActivity
import com.nane.profile.R
import com.nane.profile.databinding.ProfileFragmentBinding
import com.nane.profile.presentation.view.password.PasswordChangeActivity
import com.nane.profile.presentation.viewmodel.ProfileViewModel
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
        }
    }

    private fun setVisibleMenu(isLogin: Boolean) {
        dataBinding?.vgPasswordChange?.visibility = if (isLogin) View.VISIBLE else View.GONE
        dataBinding?.vgSetting?.visibility = if (isLogin) View.VISIBLE else View.GONE
    }
}
