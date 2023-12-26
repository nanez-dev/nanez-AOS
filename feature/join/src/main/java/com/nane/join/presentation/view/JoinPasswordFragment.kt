package com.nane.join.presentation.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.join.R
import com.nane.join.databinding.JoinPasswordFragmentBinding
import com.nane.join.presentation.data.JoinPasswordEventData
import com.nane.join.presentation.viewmodel.JoinActViewModel
import com.nane.join.presentation.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.NaneLog
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.eventObserve
import javax.inject.Inject

/**
 * Created by haul on 11/6/23
 */
@AndroidEntryPoint
class JoinPasswordFragment @Inject constructor() : BaseBindFragment<JoinPasswordFragmentBinding, JoinViewModel>(R.layout.join_password_fragment) {

    private val actViewModel: JoinActViewModel by activityViewModels()

    private var authCompletePassword = ""

    override fun createViewModel() = viewModels<JoinViewModel>().value

    override fun initFragment(dataBinding: JoinPasswordFragmentBinding, viewModel: JoinViewModel) {
        dataBinding.run {
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
                actViewModel.updatePassword(authCompletePassword)
            }
        }

        viewModel.passwordEventData.eventObserve(viewLifecycleOwner) { event ->
            when (event) {
                is JoinPasswordEventData.EnableNextBtn -> {
                    dataBinding.btnDoNext.isEnabled = event.isEnable
                    authCompletePassword = if (event.isEnable) {
                        dataBinding.editPassword.text.toString()
                    } else {
                        ""
                    }
                }
                is JoinPasswordEventData.ShowErrorView -> {
                    dataBinding.run {
                        txtError.visibility = if (event.errorText.isNullOrEmpty()) View.GONE else View.VISIBLE
                        txtError.text = event.errorText
                        if (txtError.visibility == View.VISIBLE) {
                            editPassword.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_error500_s1)
                            editPasswordCheck.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_error500_s1)
                        } else {
                            editPassword.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_line50_s1)
                            editPasswordCheck.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_line50_s1)
                        }
                    }
                }
            }
        }
    }
}