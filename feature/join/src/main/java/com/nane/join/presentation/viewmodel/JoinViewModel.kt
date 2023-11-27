package com.nane.join.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.join.domain.usecase.JoinUseCase
import com.nane.join.presentation.data.*
import com.nane.join.presentation.mapper.JoinViewMapper
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
    private val useCase: JoinUseCase,
    private val mapper: JoinViewMapper
) : BaseViewModel() {

    private val _accordList by lazy { MutableLiveData<List<JoinAccordViewData>>() }
    val accordList: LiveData<List<JoinAccordViewData>> = _accordList

    private val _emailAuthEventData by lazy { MutableLiveData<Event<JoinEmailAuthEventData>>() }
    val emailAuthEventData: LiveData<Event<JoinEmailAuthEventData>> = _emailAuthEventData

    private val _passwordEventData by lazy { MutableLiveData<Event<JoinPasswordEventData>>() }
    val passwordEventData: LiveData<Event<JoinPasswordEventData>> = _passwordEventData

    private val _nickNameEventData by lazy { MutableLiveData<Event<JoinNickNameEventData>>() }
    val nickNameEventData: LiveData<Event<JoinNickNameEventData>> = _nickNameEventData

    private val _eventCodeEventData by lazy { MutableLiveData<Event<JoinEventCodeEventData>>() }
    val eventCodeEventData: LiveData<Event<JoinEventCodeEventData>> = _eventCodeEventData



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

                    }
                    is DomainResult.Failed -> {

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


    fun checkNickNameVerify(nickName: String) {
        viewModelScope.launch {
            useCase.checkNickNameVerify(nickName).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _nickNameEventData.post(Event(JoinNickNameEventData.VerifyResult(result.data)))
                    }
                    is DomainResult.Error -> {

                    }
                    is DomainResult.Failed -> {

                    }
                }
            }
        }
    }


    fun checkEventCodeVerify(code: String) {
        viewModelScope.launch {
            useCase.checkEventCodeVerify(code).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _eventCodeEventData.post(Event(JoinEventCodeEventData.VerifyResult(result.data)))
                    }
                    is DomainResult.Error -> {

                    }
                    is DomainResult.Failed -> {

                    }
                }
            }
        }
    }


    fun getAllAccordList() {
        viewModelScope.launch {
            useCase.getAllAccordList().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _accordList.postValue(result.data.map { mapper.toAccordViewData(it) })
                    }
                    is DomainResult.Error -> {
                        _accordList.postValue(emptyList())
                    }
                    is DomainResult.Failed -> {
                        _accordList.postValue(emptyList())
                    }
                }
            }
        }
    }
}