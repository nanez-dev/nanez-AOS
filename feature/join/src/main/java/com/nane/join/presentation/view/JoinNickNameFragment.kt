package com.nane.join.presentation.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.join.R
import com.nane.join.databinding.JoinNicknameFragmentBinding
import com.nane.join.presentation.data.JoinNickNameEventData
import com.nane.join.presentation.viewmodel.JoinActViewModel
import com.nane.join.presentation.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.eventObserve
import org.techtown.nanez.utils.util.getColor
import org.techtown.nanez.utils.util.getDrawableRes

/**
 * Created by haul on 11/16/23
 */
@AndroidEntryPoint
class JoinNickNameFragment : BaseBindFragment<JoinNicknameFragmentBinding, JoinViewModel>(R.layout.join_nickname_fragment) {

    private val actViewModel: JoinActViewModel by activityViewModels()

    private var checkNickName = ""

    override fun createViewModel() = viewModels<JoinViewModel>().value

    override fun initFragment(dataBinding: JoinNicknameFragmentBinding, viewModel: JoinViewModel) {
        dataBinding.apply {
            editNickName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    val inputName = editNickName.text.toString()
                    btnDuplicateCheck.isEnabled = inputName.isNotEmpty()
                    checkNickName = inputName

                    btnDoNext.isEnabled = false
                    txtCheckResult.visibility = View.GONE
                    editNickName.background = getDrawableRes(com.nane.base.R.drawable.shape_bg50_r12_line50_s1)
                }
            })

            btnDuplicateCheck.setOnClickListener {
                if (checkNickName.isNotEmpty()) {
                    viewModel.checkNickNameVerify(checkNickName)
                }
            }

            btnDoNext.setOnClickListener {
                actViewModel.updateNickName(checkNickName)
            }
        }

        viewModel.nickNameEventData.eventObserve(viewLifecycleOwner) { event ->
            if (!isAdded) {
                return@eventObserve
            }

            when (event) {
                is JoinNickNameEventData.VerifyResult -> {
                    dataBinding.txtCheckResult.visibility = View.VISIBLE
                    if (event.isDuplicate) {
                        dataBinding.txtCheckResult.text = getString(com.nane.base.R.string.msg_join_nickname_enable)
                        dataBinding.txtCheckResult.setTextColor(getColor(com.nane.base.R.color.info_500))
                        dataBinding.editNickName.background = getDrawableRes(com.nane.base.R.drawable.shape_bg50_r12_info500_s1)
                    } else {
                        dataBinding.txtCheckResult.text = getString(com.nane.base.R.string.msg_join_nickname_disable)
                        dataBinding.txtCheckResult.setTextColor(getColor(com.nane.base.R.color.error_500))
                        dataBinding.editNickName.background = getDrawableRes(com.nane.base.R.drawable.shape_bg50_r12_error500_s1)
                    }
                    dataBinding.btnDoNext.isEnabled = event.isDuplicate
                }
            }
        }
    }
}