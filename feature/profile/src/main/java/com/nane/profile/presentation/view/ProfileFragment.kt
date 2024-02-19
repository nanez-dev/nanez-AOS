package com.nane.profile.presentation.view

import android.view.View
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import com.nane.login.presentation.view.LoginActivity
import com.nane.profile.R
import com.nane.profile.databinding.ProfileFragmentBinding
import com.nane.profile.presentation.data.ProfileLoginViewData
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.session.SessionManager

@AndroidEntryPoint
class ProfileFragment : BaseBindFragment<ProfileFragmentBinding, BaseViewModel>(R.layout.profile_fragment) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: ProfileFragmentBinding, viewModel: BaseViewModel) {
        dataBinding.run {
            actionBar.setTitle(getString(com.nane.base.R.string.label_profile))

            if (SessionManager.instance.isLoginCheck()) {
                profileLoginView.visibility = View.VISIBLE
                profileNotLoginView.visibility = View.GONE

                initLoginView(profileLoginView)
            } else {
                profileNotLoginView.visibility = View.VISIBLE
                profileLoginView.visibility = View.GONE

                initNotLoginView(profileNotLoginView)
            }

            initHelpCenterView(this)
        }
    }


    private fun initLoginView(loginView: ProfileLoginView) {
        loginView.run {
            setViewData(ProfileLoginViewData(
                nickName = "",
                email = SessionManager.instance.getUserEmail(),
                wishCount = 0,
                havingCount = 0
            ))
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
        }
    }
}
