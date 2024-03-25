package com.nane.password.presentation.view.change

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.password.R
import com.nane.password.presentation.data.PasswordNewInputEventData
import com.nane.password.presentation.viewmodel.PasswordActViewModel
import com.nane.password.presentation.viewmodel.PasswordNewInputViewModel
import com.nane.password.databinding.PasswordNewInputFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.session.SessionManager
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.eventObserve

/**
 * Created by haul on 3/24/24
 */
@AndroidEntryPoint
class PasswordNewInputFragment : BaseBindFragment<PasswordNewInputFragmentBinding, PasswordNewInputViewModel>(R.layout.password_new_input_fragment) {

    private val actViewModel: PasswordActViewModel by activityViewModels()

    override fun createViewModel() = viewModels<PasswordNewInputViewModel>().value

    override fun initFragment(dataBinding: PasswordNewInputFragmentBinding, viewModel: PasswordNewInputViewModel) {
        dataBinding.apply {

            editPasswordCheck.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    dataBinding.btnDoNext.isEnabled = false

                    val password = editPassword.text.toString()
                    val checkPassword = editPasswordCheck.text.toString()

                    if (password.isNotEmpty() && checkPassword.isNotEmpty()) {
                        viewModel.checkSamePassword(password, checkPassword)
                    }
                }
            })


            btnDoNext.setOnClickListener {
                val currentPassword = SessionManager.instance.getPassWord()
                val changePassword = editPasswordCheck.text.toString()
                if (currentPassword?.isNotEmpty() == true && changePassword.isNotEmpty()) {
                    viewModel.changeMyPassword(currentPassword, changePassword)
                }
            }
        }

        viewModel.eventData.eventObserve(viewLifecycleOwner) { event ->
            when (event) {
                is PasswordNewInputEventData.EnableNextBtn -> {
                    dataBinding.btnDoNext.isEnabled = event.isEnable
                }
                is PasswordNewInputEventData.ShowErrorView -> {
                    dataBinding.run {
                        txtError.visibility = if (event.msg.isNullOrEmpty()) View.GONE else View.VISIBLE
                        txtError.text = event.msg
                        if (txtError.visibility == View.VISIBLE) {
                            editPassword.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_error500_s1)
                            editPasswordCheck.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_error500_s1)
                        } else {
                            editPassword.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_line50_s1)
                            editPasswordCheck.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_line50_s1)
                        }
                    }
                }
                is PasswordNewInputEventData.CompleteChange -> {
                    actViewModel.completeChange()
                }
            }
        }
    }
}