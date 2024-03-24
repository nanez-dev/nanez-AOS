package com.nane.base.view.dialog

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.nane.base.R
import com.nane.base.view.dialog.data.DialogBuildData
import org.techtown.nanez.utils.NaneLog
import org.techtown.nanez.utils.util.toDp
import java.util.Random

/**
 * Created by haul on 3/24/24
 */
abstract class BaseCustomDialog<BINDING: ViewDataBinding>(@LayoutRes private val layoutId: Int) : DialogFragment() {

    abstract fun initView(binding: BINDING, buildData: DialogBuildData?)

    private lateinit var binding: BINDING
    private val dialogTag: String? = null
        get() {
            return field?.ifEmpty {
                makeCustomTag()
            }
        }

    private var dialogBuildData: DialogBuildData? = null

    fun setDialogData(data: DialogBuildData) {
        dialogBuildData = data
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.custom_dialog)
        initView(binding, dialogBuildData)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.attributes?.let {
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(it)
            lp.width = 288.toDp()
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            dialog?.window?.attributes = lp
        }
    }

    private fun makeCustomTag(): String {
        val random = Random()
        val sb = StringBuilder()
        sb.append("dl#")
        sb.append(random.nextInt(1000))
        sb.append(random.nextInt(1000))
        sb.append(random.nextInt(1000))
        return sb.toString()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialogBuildData?.onDisMiss?.invoke()
    }

    fun show(activity: FragmentActivity?) {
        if (activity == null || activity.isFinishing || activity.isDestroyed) {
            return
        }

        showsDialog = try {
            val fm = activity.supportFragmentManager
            val ft = fm.beginTransaction()
            val dialogFragment = fm.findFragmentByTag(dialogTag)
            if (dialogFragment != null) {
                ft.show(dialogFragment)
            } else if (!this.isAdded) {
                ft.add(this, dialogTag)
            }
            ft.commitAllowingStateLoss()
            true
        } catch (e: Exception) {
            NaneLog.e(e)
            false
        }
    }
}