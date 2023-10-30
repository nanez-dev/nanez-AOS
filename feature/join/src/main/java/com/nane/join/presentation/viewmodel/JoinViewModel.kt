package com.nane.join.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.join.domain.usecase.JoinUseCase
import com.nane.join.presentation.data.JoinEmailAuthEventData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by haul on 10/28/23
 */
@HiltViewModel
class JoinViewModel @Inject constructor(
    private val useCase: JoinUseCase
) : BaseViewModel() {

    private val _eventData by lazy { MutableLiveData<Event<JoinEmailAuthEventData>>() }
    val eventData: LiveData<Event<JoinEmailAuthEventData>> = _eventData


    fun sendAuthEmail(email: String) {
        viewModelScope.launch {
            useCase.postSendAuthEmail(email).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _eventData.post(Event(JoinEmailAuthEventData.SendEmailAuthResult(result.data)))
                    }
                    is DomainResult.Error -> {
                        _eventData.post(Event(JoinEmailAuthEventData.SendEmailAuthResult(false)))
                    }
                    is DomainResult.Failed -> {
                        _eventData.post(Event(JoinEmailAuthEventData.SendEmailAuthResult(false)))
                    }
                }
            }
        }
    }

    fun checkAuthEmailVerify(code: String, email: String) {
        viewModelScope.launch {
            useCase.checkAuthEmailCode(code, email).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _eventData.post(Event(JoinEmailAuthEventData.VerifyCheck(result.data)))
                    }
                    is DomainResult.Error -> {
                        _eventData.post(Event(JoinEmailAuthEventData.VerifyCheck(false)))
                    }
                    is DomainResult.Failed -> {
                        _eventData.post(Event(JoinEmailAuthEventData.VerifyCheck(false)))
                    }
                }
            }
        }
    }

}