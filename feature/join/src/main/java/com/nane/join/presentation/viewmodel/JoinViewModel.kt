package com.nane.join.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.join.domain.usecase.JoinUseCase
import com.nane.join.presentation.data.JoinEmailAuthEventData
import com.nane.join.presentation.data.JoinPasswordEventData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by haul on 10/28/23
 */
@HiltViewModel
class JoinViewModel @Inject constructor(
    private val useCase: JoinUseCase
) : BaseViewModel() {

    private val _emailAuthEventData by lazy { MutableLiveData<Event<JoinEmailAuthEventData>>() }
    val emailAuthEventData: LiveData<Event<JoinEmailAuthEventData>> = _emailAuthEventData

    private val _passwordEventData by lazy { MutableLiveData<Event<JoinPasswordEventData>>() }
    val passwordEventData: LiveData<Event<JoinPasswordEventData>> = _passwordEventData


    fun sendAuthEmail(email: String) {
        viewModelScope.launch {
            useCase.postSendAuthEmail(email).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _emailAuthEventData.post(Event(JoinEmailAuthEventData.SendEmailAuthResult(result.data)))
                    }
                    is DomainResult.Error -> {
                        _emailAuthEventData.post(Event(JoinEmailAuthEventData.SendEmailAuthResult(false)))
                    }
                    is DomainResult.Failed -> {
                        _emailAuthEventData.post(Event(JoinEmailAuthEventData.SendEmailAuthResult(false)))
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
                        _emailAuthEventData.post(Event(JoinEmailAuthEventData.VerifyCheck(result.data)))
                    }
                    is DomainResult.Error -> {
                        _emailAuthEventData.post(Event(JoinEmailAuthEventData.VerifyCheck(false)))
                    }
                    is DomainResult.Failed -> {
                        _emailAuthEventData.post(Event(JoinEmailAuthEventData.VerifyCheck(false)))
                    }
                }
            }
        }
    }


    fun checkSamePassword(password: String, passwordCheck: String) {
        val result = useCase.checkSamePassword(password, passwordCheck)
        if (!result) {
            _passwordEventData.post(Event(JoinPasswordEventData.ShowErrorView(ResUtils.instance.getString(com.nane.base.R.string.msg_error_not_same_password))))
        } else {
            checkPasswordPatten(password)
        }
    }

    private fun checkPasswordPatten(password: String) {
        val result = useCase.checkPasswordPatten(password)
        if (!result) {
            _passwordEventData.post(Event(JoinPasswordEventData.ShowErrorView(ResUtils.instance.getString(com.nane.base.R.string.msg_error_not_patten_password))))
        } else {
            _passwordEventData.post(Event(JoinPasswordEventData.ShowErrorView(null)))
        }
        _passwordEventData.post(Event(JoinPasswordEventData.EnableNextBtn(result)))
    }
}