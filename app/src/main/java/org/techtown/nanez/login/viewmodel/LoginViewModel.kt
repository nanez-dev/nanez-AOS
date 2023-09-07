package org.techtown.nanez.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.login.data.LoginEventData
import org.techtown.nanez.userinfo.domain.usecase.UserLoginInfoUseCase
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/09/04
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserLoginInfoUseCase
) : BaseViewModel() {

    private val _eventData by lazy { MutableLiveData<Event<LoginEventData>>() }
    val eventData: LiveData<Event<LoginEventData>> = _eventData

    fun requestLogin(email: String?, password: String?) {
        viewModelScope.launch {
            if (email?.isNotEmpty() == true && password?.isNotEmpty() == true) {
                userUseCase.requestLogin(email, password).collect { result ->
                    when (result) {
                        is DomainResult.Success -> {
                            _eventData.post(Event(LoginEventData.LoginSuccess))
                        }
                        is DomainResult.Failed -> {

                        }
                        is DomainResult.Error -> {

                        }
                    }
                }
            } else {
                if (email?.isEmpty() == true) {
                    _eventData.post(Event(LoginEventData.NotInputEmail))
                    return@launch
                }

                if (password?.isEmpty() == true) {
                    _eventData.post(Event(LoginEventData.NotInputPassword))
                    return@launch
                }
            }
        }
    }

}