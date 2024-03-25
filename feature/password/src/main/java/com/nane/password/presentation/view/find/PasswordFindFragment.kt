package com.nane.password.presentation.view.find

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.view.dialog.CommonTextDialog
import com.nane.base.view.dialog.data.DialogBuildData
import com.nane.password.R
import com.nane.password.databinding.PasswordFindFragmentBinding
import com.nane.password.presentation.viewmodel.PasswordFindViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.ResUtils

/**
 * Created by haul on 3/25/24
 */
@AndroidEntryPoint
class PasswordFindFragment : BaseBindFragment<PasswordFindFragmentBinding, PasswordFindViewModel>(R.layout.password_find_fragment) {

    override fun createViewModel() = viewModels<PasswordFindViewModel>().value

    override fun initFragment(dataBinding: PasswordFindFragmentBinding, viewModel: PasswordFindViewModel) {
        dataBinding.apply {

            editEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    btnSend.isEnabled = p0?.isNotEmpty() == true
                    dataBinding.txtCheckResult.visibility = View.GONE
                }
            })

            btnLogin.setOnClickListener {
                activity?.finish()
            }

            btnSend.setOnClickListener {
                viewModel.sendPasswordEmail(editEmail.text.toString())
            }
        }


        viewModel.passwordSendResult.observe(viewLifecycleOwner) { result ->
            if (result) {
                CommonTextDialog().apply {
                    setDialogData(
                        DialogBuildData(
                            title = ResUtils.instance.getString(com.nane.base.R.string.msg_complete_send_random_password),
                            positiveText = ResUtils.instance.getString(com.nane.base.R.string.label_confirm)
                        )
                    )
                }.show(activity)

                dataBinding.btnLogin.visibility = View.VISIBLE
                dataBinding.txtCheckResult.visibility = View.GONE
            } else {
                dataBinding.btnLogin.visibility = View.GONE
                dataBinding.txtCheckResult.visibility = View.VISIBLE
            }
        }
    }
}