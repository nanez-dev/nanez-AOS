package com.nane.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nane.base.R
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.post

/**
 * Created by iseungjun on 2023/08/14
 */
open class BaseViewModel : ViewModel() {

    private val _showToast by lazy { MutableLiveData<Event<String?>>() }
    val showToast = _showToast



    protected fun showErrorToast(msg: String? = null) {
        _showToast.post(Event(msg ?: ResUtils.instance.getString(R.string.msg_network_error)))
    }

}