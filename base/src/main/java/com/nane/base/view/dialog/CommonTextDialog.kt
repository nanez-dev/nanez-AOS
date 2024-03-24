package com.nane.base.view.dialog

import android.view.View
import com.nane.base.R
import com.nane.base.databinding.CommonTextDialogBinding
import com.nane.base.view.dialog.data.DialogBuildData
import org.techtown.nanez.utils.util.getColor

/**
 * Created by haul on 3/24/24
 */
class CommonTextDialog : BaseCustomDialog<CommonTextDialogBinding>(R.layout.common_text_dialog) {

    override fun initView(binding: CommonTextDialogBinding, buildData: DialogBuildData?) {
        binding.apply {
            txtTitle.text = buildData?.title
            if ((buildData?.titleTextColor ?: 0) > 0) {
                txtTitle.setTextColor(getColor(buildData?.titleTextColor ?: R.color.brand_500))
            }

            if (buildData?.content?.isNotEmpty() == true) {
                txtContent.visibility = View.VISIBLE
                txtContent.text = buildData.content
            } else {
                txtContent.visibility = View.GONE
            }

            if ((buildData?.negativeBtnTextColor ?: 0) > 0) {
                btnNegative.setTextColor(getColor(buildData?.negativeBtnTextColor ?: 0))
            }

            if (buildData?.negativeText?.isNotEmpty() == true) {
                btnNegative.visibility = View.VISIBLE
                btnLineView.visibility = View.VISIBLE
                btnNegative.text = buildData.negativeText
                btnNegative.setOnClickListener {
                    buildData.onNegativeAction?.invoke()
                    dismiss()
                }
            } else {
                btnLineView.visibility = View.GONE
                btnNegative.visibility = View.GONE
            }

            if ((buildData?.positiveTextColor ?: 0) > 0) {
                btnPositive.setTextColor(getColor(buildData?.positiveTextColor ?: 0))
            }

            if (buildData?.positiveText?.isNotEmpty() == true) {
                btnPositive.visibility = View.VISIBLE
                btnPositive.text = buildData.positiveText
                btnPositive.setOnClickListener {
                    buildData.onPositiveAction?.invoke()
                    dismiss()
                }
            } else {
                btnPositive.visibility = View.GONE
            }
        }
    }

}