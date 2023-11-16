package com.nane.join.presentation.view

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.join.R
import com.nane.join.databinding.JoinEmailAuthFragmentBinding
import com.nane.join.presentation.data.JoinEmailAuthEventData
import com.nane.join.presentation.viewmodel.JoinActViewModel
import com.nane.join.presentation.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.format
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.eventObserve

/**
 * Created by haul on 10/29/23
 */
@AndroidEntryPoint
class JoinEmailAuthFragment : BaseBindFragment<JoinEmailAuthFragmentBinding, JoinViewModel>(R.layout.join_email_auth_fragment) {

    private val actViewModel: JoinActViewModel by activityViewModels()

    private val authTimer by lazy {
        object : CountDownTimer(AUTH_TIME, AUTH_INTERVAL) {
            override fun onTick(p0: Long) {
                val time = p0 / AUTH_INTERVAL

                val minute = format("%02d", time / 60)
                val sec = format("%02d", time % 60)

                dataBinding?.txtTime?.text = "${minute}:${sec}"
            }

            override fun onFinish() {
                if (!isCheckAuth) {
                    dataBinding?.btnSendAuth?.isEnabled = false
                    dataBinding?.btnDoNext?.isEnabled = false
                }
            }
        }
    }

    private var isSendAuth = false
    private var isCheckAuth = false

    override fun createViewModel() = viewModels<JoinViewModel>().value

    override fun initFragment(dataBinding: JoinEmailAuthFragmentBinding, viewModel: JoinViewModel) {
        dataBinding.apply {

            editEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    if (isSendAuth) {
                        return
                    }

                    dataBinding.btnSendAuth.isEnabled = p0?.toString()?.isNotEmpty() == true
                }
            })

            editEmailAuth.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    if (isSendAuth) {
                        if (isCheckAuth) {
                            return
                        }

                        dataBinding.btnSendAuth.isEnabled = p0?.toString()?.isNotEmpty() == true
                    }
                }
            })

            btnSendAuth.setOnClickListener {
                viewModel.sendAuthEmail(editEmail.text.toString())
            }

            btnReSendAuth.setOnClickListener {
                viewModel.sendAuthEmail(editEmail.text.toString())
            }

            btnDoNext.setOnClickListener {
                actViewModel.postNextStep()
            }
        }

        viewModel.emailAuthEventData.eventObserve(viewLifecycleOwner) { event ->
            if (!isAdded) {
                return@eventObserve
            }

            when (event) {
                is JoinEmailAuthEventData.SendEmailAuthResult -> {
                    isSendAuth = event.isSuccess
                    isCheckAuth = false
                    dataBinding.btnDoNext.isEnabled = false

                    checkAuthResultView(event.isSuccess)

                    if (isSendAuth) {
                        authTimer.start()
                    } else {
                        authTimer.cancel()
                    }
                }
                is JoinEmailAuthEventData.VerifyCheck -> {
                    isCheckAuth = event.isSuccessVerify
                    dataBinding.btnDoNext.isEnabled = isCheckAuth

                    if (isCheckAuth) {
                        dataBinding.btnSendAuth.isEnabled = false
                        authTimer.cancel()
                    }
                }
            }
        }
    }


    private fun checkAuthResultView(isSuccess: Boolean) {
        dataBinding?.run {
            if (isSuccess) {
                vgInputAuth.visibility = View.VISIBLE
                btnReSendAuth.visibility = View.VISIBLE
                txtTime.visibility = View.VISIBLE

                btnSendAuth.text = ResUtils.instance.getString(com.nane.base.R.string.label_auth_verify)
                btnSendAuth.isEnabled = editEmailAuth.text.toString().isNotEmpty() == true
                btnSendAuth.setOnClickListener {
                    val code = editEmailAuth.text.toString()
                    val email = editEmail.text.toString()
                    if (code.isNotEmpty() && email.isNotEmpty()) {
                        viewModel?.checkAuthEmailVerify(code, email)
                    }
                }
            } else {
                vgInputAuth.visibility = View.GONE
                btnReSendAuth.visibility = View.GONE
                txtTime.visibility = View.GONE

                btnSendAuth.text = ResUtils.instance.getString(com.nane.base.R.string.label_send_auth)
                btnSendAuth.isEnabled = editEmail.text.toString().isNotEmpty() == true
                btnSendAuth.setOnClickListener {
                    val email = editEmail.text.toString()
                    if (email.isNotEmpty()) {
                        viewModel?.sendAuthEmail(email)
                    }
                }
            }
        }
    }


    companion object {
        private const val AUTH_TIME = 3 * 60 * 1000L
        private const val AUTH_INTERVAL = 1000L
    }
}