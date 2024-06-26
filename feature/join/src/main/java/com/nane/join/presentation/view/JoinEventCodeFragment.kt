package com.nane.join.presentation.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.view.dialog.CommonTextDialog
import com.nane.base.view.dialog.data.DialogBuildData
import com.nane.join.R
import com.nane.join.databinding.JoinEventCodeFragmentBinding
import com.nane.join.presentation.data.JoinEventCodeEventData
import com.nane.join.presentation.viewmodel.JoinActViewModel
import com.nane.join.presentation.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.eventObserve

/**
 * Created by haul on 11/27/23
 */
@AndroidEntryPoint
class JoinEventCodeFragment : BaseBindFragment<JoinEventCodeFragmentBinding, JoinViewModel>(R.layout.join_event_code_fragment) {

    private val actViewModel: JoinActViewModel by activityViewModels()

    override fun createViewModel() = viewModels<JoinViewModel>().value

    override fun initFragment(dataBinding: JoinEventCodeFragmentBinding, viewModel: JoinViewModel) {
        dataBinding.apply {
            btnSignUp.setOnClickListener {
                actViewModel.postSignUp()
            }

            btnSkip.setOnClickListener {
                actViewModel.updateEventCode(null)
                actViewModel.postSignUp()
            }

            btnConfirm.setOnClickListener {
                val code = editEventCode.text?.toString()

                if (code?.isNotEmpty() == true) {
                    viewModel.checkEventCodeVerify(code)
                }
            }

            editEventCode.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    btnConfirm.isEnabled = p0?.toString()?.isNotEmpty() == true
                }
            })
        }

        viewModel.eventCodeEventData.eventObserve(viewLifecycleOwner) { event ->
            when (event) {
                is JoinEventCodeEventData.VerifyResult -> {
                    dataBinding.btnSignUp.isEnabled = event.isEnable
                    dataBinding.txtCheckResult.visibility = if (event.isEnable) View.VISIBLE else View.GONE

                    CommonTextDialog().apply {
                        setDialogData(
                            DialogBuildData(
                                titleTextColor = if (event.isEnable) {
                                    com.nane.base.R.color.brand_500
                                } else {
                                    com.nane.base.R.color.error_500
                                },
                                title = if (event.isEnable) {
                                    ResUtils.instance.getString(com.nane.base.R.string.msg_complete_check)
                                } else {
                                    ResUtils.instance.getString(com.nane.base.R.string.msg_not_match_event_code)
                                },
                                positiveText = ResUtils.instance.getString(com.nane.base.R.string.label_confirm)
                            )
                        )
                    }.show(activity)


                    if (event.isEnable) {
                        actViewModel.updateEventCode(event.code)
                    } else {
                        actViewModel.updateEventCode(null)
                    }
                }
            }
        }
    }
}