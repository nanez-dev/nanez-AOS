package com.nane.password.presentation.view.find

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.base.viewmodel.BaseViewModel
import com.nane.password.R
import com.nane.password.databinding.PasswordFindActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.addFragment

/**
 * Created by haul on 3/25/24
 */
@AndroidEntryPoint
class PasswordFindActivity : BaseBindActivity<PasswordFindActivityBinding, BaseViewModel>(R.layout.password_find_activity) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initActivity(dataBinding: PasswordFindActivityBinding, viewModel: BaseViewModel) {
        dataBinding.apply {
            with(actionBar) {
                setTitle(getString(com.nane.base.R.string.label_find_password))
                setUseBackBtn { onActionBackPressed() }
            }

            addFragment(container, null, "PasswordFindFragment") {
                PasswordFindFragment()
            }
        }
    }

    override fun onActionBackPressed() {
        finish()
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, PasswordFindActivity::class.java)
        }
    }
}