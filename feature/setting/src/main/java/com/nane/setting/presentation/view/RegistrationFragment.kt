package com.nane.setting.presentation.view

import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.activityViewModels
import com.nane.setting.R
import com.nane.base.view.BaseBindFragment
import com.nane.setting.databinding.RegistrationFragmentBinding
import com.nane.setting.presentation.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.hideImeService
import org.techtown.nanez.utils.util.showImeService

@AndroidEntryPoint
class RegistrationFragment :
    BaseBindFragment<RegistrationFragmentBinding, RegistrationViewModel>(R.layout.registration_fragment) {

    private val actViewModel: RegistrationViewModel by activityViewModels()

    override fun createViewModel(): RegistrationViewModel = actViewModel

    override fun initFragment(
        dataBinding: RegistrationFragmentBinding,
        viewModel: RegistrationViewModel
    ) {
        when (arguments?.getString(RegistrationActivity.REQUEST_ITEM)) {
            RegistrationActivity.REQUEST_BRAND -> {
                with(dataBinding) {
                    textTitle.text = ResUtils.instance.getString(com.nane.base.R.string.msg_title_request_new_perfume_brand_name)
                    textRequestTitle.text = ResUtils.instance.getString(com.nane.base.R.string.label_brand_name)
                    editRequest.hint = ResUtils.instance.getString(com.nane.base.R.string.msg_hint_request_new_perfume_brand_name)
                    btnDoNext.setOnClickListener {
                        viewModel.registerBrandName(editRequest.text?.toString() ?: "")
                    }
                }
            }
            RegistrationActivity.REQUEST_PERFUME -> {
                with(dataBinding) {
                    textTitle.text = ResUtils.instance.getString(com.nane.base.R.string.msg_title_request_new_perfume_name)
                    textRequestTitle.text = ResUtils.instance.getString(com.nane.base.R.string.label_perfume_name)
                    editRequest.hint = ResUtils.instance.getString(com.nane.base.R.string.msg_hint_request_new_perfume_name)
                    btnDoNext.text = ResUtils.instance.getString(com.nane.base.R.string.label_request)
                    btnDoNext.setOnClickListener {
                        editRequest.hideImeService()
                        viewModel.registerPerfumeName(editRequest.text?.toString() ?: "")
                    }
                }
            }
        }

        with(dataBinding) {
            editRequest.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    btnDoNext.isEnabled = s?.isNotEmpty() == true
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        dataBinding?.editRequest?.showImeService()
    }
}