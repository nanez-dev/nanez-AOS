package com.nane.password.presentation.view.change

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import com.nane.password.R
import com.nane.password.presentation.viewmodel.PasswordActViewModel
import com.nane.password.databinding.PasswordOriginInputFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.session.SessionManager
import org.techtown.nanez.utils.util.ResUtils

/**
 * Created by haul on 3/24/24
 */
@AndroidEntryPoint
class PasswordOriginInputFragment : BaseBindFragment<PasswordOriginInputFragmentBinding, BaseViewModel>(R.layout.password_origin_input_fragment) {

    private val actViewModel: PasswordActViewModel by activityViewModels()

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: PasswordOriginInputFragmentBinding, viewModel: BaseViewModel) {
        dataBinding.apply {

            editPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    val inputPassword = p0?.toString()
                    if (inputPassword?.isEmpty() == true) {
                        btnDoNext.isEnabled = false
                        txtError.visibility = View.GONE
                        editPassword.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_line50_s1)
                        return
                    }

                    if (inputPassword != SessionManager.instance.getPassWord()) {
                        btnDoNext.isEnabled = false
                        txtError.visibility = View.VISIBLE
                        txtError.text = getString(com.nane.base.R.string.msg_error_not_same_password)
                        editPassword.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_error500_s1)
                    } else {
                        btnDoNext.isEnabled = true
                        txtError.visibility = View.GONE
                        editPassword.background = ResUtils.getDrawable(context, com.nane.base.R.drawable.shape_bg50_r12_line50_s1)
                    }
                }
            })


            btnDoNext.setOnClickListener {
                actViewModel.moveToNextStep()
            }
        }
    }
}