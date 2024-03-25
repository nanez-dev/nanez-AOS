package com.nane.password.presentation.view.change

import android.content.Intent
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import com.nane.password.R
import com.nane.password.databinding.PasswordChangeCompleteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by haul on 3/24/24
 */
@AndroidEntryPoint
class PasswordChangeCompleteFragment : BaseBindFragment<PasswordChangeCompleteFragmentBinding, BaseViewModel>(R.layout.password_change_complete_fragment) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: PasswordChangeCompleteFragmentBinding, viewModel: BaseViewModel) {
        dataBinding.btnLogin.setOnClickListener {
            activity?.let {
                val intent = Intent(it, Class.forName("com.nane.login.presentation.view.LoginActivity")).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                it.startActivity(intent)
                it.finish()
            }
        }
    }
}