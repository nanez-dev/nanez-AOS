package com.nane.profile.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.nane.profile.databinding.ProfileLoginViewBinding
import com.nane.profile.databinding.ProfileNoLoginViewBinding
import org.techtown.nanez.utils.util.ResUtils

/**
 * Created by haul on 2/19/24
 */
class ProfileNoLoginView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding = ProfileNoLoginViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var userActionListener: UserActionListener? = null
    interface UserActionListener {
        fun onClickLogin()
    }


    init {
        binding.apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            setBackgroundColor(ResUtils.getColor(context, com.nane.base.R.color.white))

            btnLogin.setOnClickListener {
                userActionListener?.onClickLogin()
            }
        }
    }



    fun setUserActionListener(listener: UserActionListener) {
        this.userActionListener = listener
    }
}