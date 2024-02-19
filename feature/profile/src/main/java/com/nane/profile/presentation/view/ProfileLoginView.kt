package com.nane.profile.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.nane.profile.databinding.ProfileLoginViewBinding
import com.nane.profile.presentation.data.ProfileLoginViewData
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.getString

/**
 * Created by haul on 2/19/24
 */
class ProfileLoginView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding = ProfileLoginViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            setBackgroundColor(ResUtils.getColor(context, com.nane.base.R.color.white))
        }
    }


    fun setViewData(data: ProfileLoginViewData) {
        binding.run {
            txtLoginGuide.text = getString(com.nane.base.R.string.msg_profile_name_content, data.nickName ?: "")
            txtUserEmail.text = data.email

            txtWishListNumber.text = "${data.wishCount}"
            txtHavingListNumber.text = "${data.havingCount}"
        }
    }

}