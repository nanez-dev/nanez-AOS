package com.nane.join.presentation.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.join.R
import com.nane.join.databinding.JoinEventCodeFragmentBinding
import com.nane.join.presentation.data.JoinEventCodeEventData
import com.nane.join.presentation.viewmodel.JoinActViewModel
import com.nane.join.presentation.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.eventObserve

/**
 * Created by haul on 11/27/23
 */
@AndroidEntryPoint
class JoinEventCodeFragment : BaseBindFragment<JoinEventCodeFragmentBinding, JoinViewModel>(R.layout.join_event_code_fragment) {

    private val actViewModel: JoinActViewModel by activityViewModels()

    private var authCompleteCode: String? = null

    override fun createViewModel() = viewModels<JoinViewModel>().value

    override fun initFragment(dataBinding: JoinEventCodeFragmentBinding, viewModel: JoinViewModel) {
        dataBinding.apply {
            btnSignUp.setOnClickListener {
                actViewModel.updateEventCode(authCompleteCode)
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
                    authCompleteCode = if (event.isEnable) {
                        dataBinding.editEventCode.text.toString()
                    } else {
                        null
                    }
                }
            }
        }
    }
}