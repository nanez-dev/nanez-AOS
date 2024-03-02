package com.nane.join.presentation.view

import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.join.R
import com.nane.join.databinding.JoinAgreementFragmentBinding
import com.nane.join.presentation.viewmodel.JoinActViewModel
import com.nane.join.presentation.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.ResUtils

/**
 * Created by haul on 10/28/23
 */
@AndroidEntryPoint
class JoinAgreementFragment : BaseBindFragment<JoinAgreementFragmentBinding, JoinViewModel>(R.layout.join_agreement_fragment) {

    private val actViewModel: JoinActViewModel by activityViewModels()

    override fun createViewModel() = viewModels<JoinViewModel>().value

    override fun initFragment(dataBinding: JoinAgreementFragmentBinding, viewModel: JoinViewModel) {
        dataBinding.apply {
            btnAllAgree.setOnClickListener {
                btnAllAgree.isSelected = !btnAllAgree.isSelected

                selectedDrawable(btnAllAgree, btnAllAgree.isSelected)
                selectedDrawable(txtServiceAgree, btnAllAgree.isSelected)
                selectedDrawable(txtPersonalAgree, btnAllAgree.isSelected)
                selectedDrawable(txtMarketingAgree, btnAllAgree.isSelected)

                checkAgreeBtnEnable()
            }

            txtServiceAgree.setOnClickListener {
                selectedDrawable(txtServiceAgree, !txtServiceAgree.isSelected)
                checkAllAgreeSelect()
                checkAgreeBtnEnable()
            }

            txtPersonalAgree.setOnClickListener {
                selectedDrawable(txtPersonalAgree, !txtPersonalAgree.isSelected)
                checkAllAgreeSelect()
                checkAgreeBtnEnable()
            }

            txtMarketingAgree.setOnClickListener {
                selectedDrawable(txtMarketingAgree, !txtMarketingAgree.isSelected)
                checkAllAgreeSelect()
                checkAgreeBtnEnable()
            }

            btnDoAgree.setOnClickListener {
                actViewModel.updateMarketingAgree(txtMarketingAgree.isSelected)
            }
        }
    }


    private fun selectedDrawable(targetView: AppCompatTextView, isSelect: Boolean) {
        val tintColor = if (isSelect) {
            ResUtils.getColor(context, com.nane.base.R.color.brand_500)
        } else {
            ResUtils.getColor(context, com.nane.base.R.color.gray_300)
        }
        targetView.compoundDrawables.firstOrNull()?.setTint(tintColor)
        targetView.isSelected = isSelect
    }


    private fun checkAllAgreeSelect() {
        dataBinding?.run {
            btnAllAgree.isSelected = txtServiceAgree.isSelected && txtPersonalAgree.isSelected && txtMarketingAgree.isSelected
            selectedDrawable(btnAllAgree, btnAllAgree.isSelected)
        }
    }


    private fun checkAgreeBtnEnable() {
        dataBinding?.run {
            btnDoAgree.isEnabled = txtServiceAgree.isSelected && txtPersonalAgree.isSelected
        }
    }
}