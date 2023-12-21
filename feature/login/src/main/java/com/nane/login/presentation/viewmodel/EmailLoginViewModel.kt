package com.nane.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.login.domain.usecase.UserLoginInfoUseCase
import com.nane.login.presentation.data.EmailLoginEventData
import com.nane.network.api.FailedMessageConst
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/09/04
 */
@HiltViewModel
class EmailLoginViewModel @Inject constructor(
    private val userUseCase: UserLoginInfoUseCase
) : BaseViewModel() {

    private val _eventData by lazy { MutableLiveData<Event<EmailLoginEventData>>() }
    val eventData: LiveData<Event<EmailLoginEventData>> = _eventData

    fun requestLogin(email: String?, password: String?) {
        viewModelScope.launch {
            if (email?.isNotEmpty() == true && password?.isNotEmpty() == true) {
                userUseCase.requestLogin(email, password).collect { result ->
                    when (result) {
                        is DomainResult.Success -> {
                            _eventData.post(Event(EmailLoginEventData.LoginSuccess))
                        }
                        is DomainResult.Failed -> {
                            when (result.msg) {
                                FailedMessageConst.NOT_FOUND_USER -> {
                                    _eventData.post(Event(EmailLoginEventData.NotFoundLoginInfo))
                                }
                                FailedMessageConst.NOT_MATCH_USER,
                                FailedMessageConst.INVALID_PASSWORD -> {
                                    _eventData.post(Event(EmailLoginEventData.NotMatchLoginInfo))
                                }
                                else -> {
                                    showErrorToast()
                                }
                            }
                        }
                        is DomainResult.Error -> {
                            showErrorToast()
                        }
                    }
                }
            } else {
                if (email?.isEmpty() == true) {
                    showErrorToast(ResUtils.instance.getString(com.nane.base.R.string.msg_hint_input_email))
                    return@launch
                }

                if (password?.isEmpty() == true) {
                    showErrorToast(ResUtils.instance.getString(com.nane.base.R.string.msg_hint_input_password))
                    return@launch
                }
            }
        }
    }

}