package com.nane.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.nane.base.databinding.ActionbarViewBinding
import org.techtown.nanez.utils.util.ResUtils

/**
 * Created by iseungjun on 2023/08/19
 */
class ActionbarView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null) : ConstraintLayout(context, attr) {

    private val binding = ActionbarViewBinding.inflate(LayoutInflater.from(context), this, true)

    var actionListener: ActionListener? = null
    interface ActionListener {
        fun onClickRight()
    }

    init {
        binding.apply {
            btnRight.setOnClickListener {
                actionListener?.onClickRight()
            }

            mainImg.visibility = View.GONE
            btnRight.visibility = View.GONE
            txtTitle.visibility = View.VISIBLE
        }
    }

    fun setHomeImg(isShow: Boolean) {
        binding.mainImg.visibility = if (isShow) View.VISIBLE else View.GONE
        binding.txtTitle.visibility = if (isShow) View.GONE else View.VISIBLE
    }

    fun setTitle(title: String?) {
        binding.txtTitle.text = title
    }

    fun setUseBackBtn(onAction: () -> Unit) {
        binding.btnBack.visibility = View.VISIBLE
        binding.btnBack.setOnClickListener {
            onAction.invoke()
        }
    }

    fun setRightBtn(@DrawableRes imgRes: Int) {
        binding.btnRight.setImageDrawable(ResUtils.getDrawable(context, imgRes))
        binding.btnRight.visibility = View.VISIBLE
    }

    fun setLineViewVisible(isShow: Boolean) {
        binding.lineView.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}