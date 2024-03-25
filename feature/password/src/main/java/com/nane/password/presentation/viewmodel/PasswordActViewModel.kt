package com.nane.password.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nane.base.viewmodel.BaseViewModel
import com.nane.password.presentation.data.PasswordActEventData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by haul on 3/24/24
 */
@HiltViewModel
class PasswordActViewModel @Inject constructor() : BaseViewModel() {

    private val _eventData by lazy { MutableLiveData<Event<PasswordActEventData>>() }
    val eventData: LiveData<Event<PasswordActEventData>> get() = _eventData

    fun moveToNextStep() {
        _eventData.post(Event(PasswordActEventData.MoveToInputNewPassword))
    }

    fun completeChange() {
        _eventData.post(Event(PasswordActEventData.MoveToCompleteChange))
    }
}