package com.nane.profile.presentation.view.password

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.profile.R
import com.nane.profile.databinding.PasswordChangeAcitivityBinding
import com.nane.profile.presentation.data.PasswordActEventData
import com.nane.profile.presentation.viewmodel.PasswordActViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.addFragment
import org.techtown.nanez.utils.util.eventObserve

/**
 * Created by haul on 3/24/24
 */
@AndroidEntryPoint
class PasswordChangeActivity : BaseBindActivity<PasswordChangeAcitivityBinding, PasswordActViewModel>(R.layout.password_change_acitivity) {

    private var isMoveComplete = false

    override fun createViewModel() = viewModels<PasswordActViewModel>().value

    override fun initActivity(dataBinding: PasswordChangeAcitivityBinding, viewModel: PasswordActViewModel) {
        dataBinding.apply {
            with(actionBar) {
                setTitle(getString(com.nane.base.R.string.label_change_password))
                setUseBackBtn { onActionBackPressed() }
            }

            addFragment(container, tag = "PasswordOriginInputFragment") {
                PasswordOriginInputFragment()
            }
        }

        viewModel.eventData.eventObserve(this) { event ->
            when (event) {
                is PasswordActEventData.MoveToInputNewPassword -> {
                    addFragment(dataBinding.container, tag = "PasswordNewInputFragment" ) {
                        PasswordNewInputFragment()
                    }
                }
                is PasswordActEventData.MoveToCompleteChange -> {
                    isMoveComplete = true
                    dataBinding.actionBar.visibility = View.GONE
                    addFragment(dataBinding.container, tag = "PasswordChangeCompleteFragment" ) {
                        PasswordChangeCompleteFragment()
                    }
                }
            }
        }
    }

    override fun onActionBackPressed() {
        if (!isMoveComplete) {
            finish()
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, PasswordChangeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }
}