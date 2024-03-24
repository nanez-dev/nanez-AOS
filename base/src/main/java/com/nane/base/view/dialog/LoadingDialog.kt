package com.nane.base.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.nane.base.databinding.LoadingDialogBinding

/**
 * Created by haul on 1/2/24
 */
class LoadingDialog(context: Context) : Dialog(context) {

    private val binding by lazy { LoadingDialogBinding.inflate(LayoutInflater.from(context), null, false) }

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(binding.root)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND) // dialog의 dim 처리 배경 제거
    }

    fun showLoading() {
        show()
        binding.lottieView.playAnimation()
    }

    fun hideLoading() {
        if (!isShowing) {
            return
        }

        binding.lottieView.postDelayed({
            binding.lottieView.cancelAnimation()
        }, 1000)
        dismiss()
    }
}