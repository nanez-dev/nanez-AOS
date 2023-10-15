package com.nane.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nane.base.viewmodel.BaseViewModel
import com.nane.login.presentation.data.LoginActEventData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/10/15
 */
@HiltViewModel
class LoginActViewModel @Inject constructor() : BaseViewModel() {

    private val _eventData by lazy { MutableLiveData<Event<LoginActEventData>>() }
    val eventData: LiveData<Event<LoginActEventData>> = _eventData


    fun onEventAction(event: LoginActEventData) {
        _eventData.post(Event(event))
    }
}