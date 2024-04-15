package com.nane.base.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.nane.base.view.dialog.LoadingDialog
import com.nane.base.viewmodel.BaseViewModel
import org.techtown.nanez.utils.util.eventObserve

/**
 * Created by iseungjun on 2023/08/14
 */
abstract class BaseBindActivity<VIEW: ViewDataBinding, VM: BaseViewModel>(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {

    abstract fun createViewModel(): VM
    abstract fun initActivity(dataBinding: VIEW, viewModel: VM)
    abstract fun onActionBackPressed()

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onActionBackPressed()
        }
    }

    private val loadingDialog by lazy { LoadingDialog(this) }

    private lateinit var _dataBinding: VIEW
    private lateinit var _viewModel: VM

    protected val viewModel: VM?
        get() = if (::_viewModel.isInitialized) {
            _viewModel
        } else {
            null
        }

    protected val dataBinding: VIEW?
        get() = if (::_dataBinding.isInitialized) {
            _dataBinding
        } else {
            null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, callback)

        _dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        _viewModel = createViewModel()

        _dataBinding.apply {
            lifecycleOwner = this@BaseBindActivity
        }

        if (::_dataBinding.isInitialized && ::_viewModel.isInitialized) {
            initActivity(_dataBinding, _viewModel)
        }


        _viewModel.showToast.eventObserve(this) {
            showToast(it)
        }

        _viewModel.showLoading.eventObserve(this) {
            showLoading(it)
        }
    }

    private fun showToast(msg: String?) {
        if (msg?.isNotEmpty() == true) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun showLoading(isShow: Boolean) {
        if (isShow) {
            loadingDialog.showLoading()
        } else {
            loadingDialog.hideLoading()
        }
    }
}