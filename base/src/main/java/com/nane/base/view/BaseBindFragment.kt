package com.nane.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.nane.base.viewmodel.BaseViewModel
import org.techtown.nanez.utils.util.eventObserve

/**
 * Created by iseungjun on 2023/08/14
 */
abstract class BaseBindFragment<VIEW: ViewDataBinding, VM: BaseViewModel>(@LayoutRes private val layoutRes: Int) : Fragment() {

    abstract fun createViewModel(): VM
    abstract fun initFragment(dataBinding: VIEW, viewModel: VM)

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

    protected var isBackPressedInterceptor: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _dataBinding = DataBindingUtil.inflate<VIEW>(inflater, layoutRes, container, false)
        _viewModel = createViewModel()

        _dataBinding.apply {
            lifecycleOwner = this@BaseBindFragment
        }

        _viewModel.showToast.eventObserve(viewLifecycleOwner) {
            showToast(it)
        }
        return _dataBinding.root
    }


    open fun onBackPressedInterceptor(): Boolean {
        return isBackPressedInterceptor
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (::_dataBinding.isInitialized && ::_viewModel.isInitialized) {
            initFragment(_dataBinding, _viewModel)
        }
    }

    protected fun showToast(msg: String?) {
        if (msg?.isNotEmpty() == true) {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }
    }
}