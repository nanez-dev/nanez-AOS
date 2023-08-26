package org.techtown.nanez.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import org.techtown.nanez.databinding.ActionbarViewBinding
import org.techtown.nanez.utils.util.ResUtils

/**
 * Created by iseungjun on 2023/08/19
 */
class ActionbarView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null) : ConstraintLayout(context, attr) {

    private val binding = ActionbarViewBinding.inflate(LayoutInflater.from(context), this, true)

    var actionListener: ActionListener? = null
    interface ActionListener {
        fun onClickBack()
        fun onClickRight()
    }

    init {
        binding.apply {
            btnBack.setOnClickListener {
                actionListener?.onClickBack()
            }

            btnRight.setOnClickListener {
                actionListener?.onClickRight()
            }

            mainImg.visibility = View.GONE
            btnRight.visibility = View.GONE
        }
    }

    fun setHomeImg(isShow: Boolean) {
        binding.mainImg.visibility = if (isShow) View.VISIBLE else View.GONE
        binding.txtTitle.visibility = if (isShow) View.GONE else View.VISIBLE
    }

    fun setTitle(title: String?) {
        binding.txtTitle.text = title
    }

    fun setRightBtn(@DrawableRes imgRes: Int) {
        binding.btnRight.setImageDrawable(ResUtils.getDrawable(context, imgRes))
        binding.btnRight.visibility = View.VISIBLE
    }
}