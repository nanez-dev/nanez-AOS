package com.nane.login.presentation.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.join.presentation.view.JoinActivity
import com.nane.login.R
import com.nane.login.databinding.EmailLoginFragmentBinding
import com.nane.login.presentation.data.EmailLoginEventData
import com.nane.login.presentation.viewmodel.EmailLoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.eventObserve

@AndroidEntryPoint
class EmailLoginFragment : BaseBindFragment<EmailLoginFragmentBinding, EmailLoginViewModel>(R.layout.email_login_fragment) {

    private val clearErrorTextWatcher by lazy {
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                clearErrorView()
            }
        }
    }

    override fun createViewModel() = viewModels<EmailLoginViewModel>().value

    override fun initFragment(dataBinding: EmailLoginFragmentBinding, viewModel: EmailLoginViewModel) {
        dataBinding.apply {
            btnLogin.setOnClickListener {
                viewModel.requestLogin(editEmail.text?.toString(), editPassword.text?.toString())
            }

            btnJoin.setOnClickListener {
                activity?.let { act ->
                    startActivity(JoinActivity.createIntent(act))
                }

            }

            editEmail.addTextChangedListener(clearErrorTextWatcher)
            editPassword.addTextChangedListener(clearErrorTextWatcher)
        }

        viewModel.eventData.eventObserve(this) { event ->
            when (event) {
                is EmailLoginEventData.LoginSuccess -> {

                }
                is EmailLoginEventData.NotFoundLoginInfo -> {
                    errorTextView(ResUtils.instance.getString(com.nane.base.R.string.msg_error_login_not_match_user))
                }
                is EmailLoginEventData.NotMatchLoginInfo -> {
                    errorTextView(ResUtils.instance.getString(com.nane.base.R.string.msg_error_login_not_match_id_or_passward))
                }
            }
        }
    }

    private fun errorTextView(msg: String) {
        dataBinding?.run {
            txtError.text = msg
            txtError.visibility = View.VISIBLE

            editEmail.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_white_r20_error500_s1)
            editPassword.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_white_r20_error500_s1)
        }
    }

    private fun clearErrorView() {
        dataBinding?.run {
            txtError.visibility = View.GONE

            editEmail.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_white_r20_gray3_s1)
            editPassword.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_white_r20_gray3_s1)
        }
    }
}