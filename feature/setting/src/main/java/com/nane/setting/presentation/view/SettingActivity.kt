package com.nane.setting.presentation.view

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.dialog.CommonTextDialog
import com.nane.base.view.dialog.data.DialogBuildData
import com.nane.setting.R
import com.nane.setting.databinding.SettingActivityBinding
import com.nane.setting.presentation.data.SettingEvent
import com.nane.setting.presentation.viewmodel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.eventObserve

@AndroidEntryPoint
class SettingActivity :
    BaseBindActivity<SettingActivityBinding, SettingViewModel>(R.layout.setting_activity) {

    override fun createViewModel(): SettingViewModel = viewModels<SettingViewModel>().value

    override fun onActionBackPressed() {
        finish()
    }

    override fun initActivity(dataBinding: SettingActivityBinding, viewModel: SettingViewModel) {
        with(dataBinding) {
            actionBar.setTitle(ResUtils.instance.getString(com.nane.base.R.string.label_profile_setting))
            actionBar.setUseBackBtn {
                finish()
            }
            btnLogOut.setOnClickListener(clickListener)
            btnWithdraw.setOnClickListener(clickListener)
        }

        with(viewModel) {
            eventData.eventObserve(this@SettingActivity) { event ->
                when (event) {
                    is SettingEvent.LogoutEvent -> {
                        finish()
                    }
                }
            }
        }
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            dataBinding?.btnLogOut?.id -> {
                CommonTextDialog().apply {
                    setDialogData(
                        DialogBuildData(
                            title = ResUtils.instance.getString(com.nane.base.R.string.msg_confirm_logout),
                            positiveText = ResUtils.instance.getString(com.nane.base.R.string.label_logout),
                            negativeText = ResUtils.instance.getString(com.nane.base.R.string.label_cancel),
                            onPositiveAction = {
                                viewModel?.logOut()
                            }
                        )
                    )
                }.show(this)
            }
            dataBinding?.btnWithdraw?.id -> {
                CommonTextDialog().apply {
                    setDialogData(
                        DialogBuildData(
                            title = ResUtils.instance.getString(com.nane.base.R.string.msg_confirm_withdraw),
                            content = ResUtils.instance.getString(com.nane.base.R.string.msg_explain_withdraw),
                            positiveText = ResUtils.instance.getString(com.nane.base.R.string.label_withdraw),
                            negativeText = ResUtils.instance.getString(com.nane.base.R.string.label_cancel),
                            onPositiveAction = {
                                viewModel?.withdraw()
                            }
                        )
                    )
                }.show(this)
            }
            else -> {}
        }
    }

    private fun showToast(msg: String?) {
        if (msg?.isNotEmpty() == true) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SettingActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }
}