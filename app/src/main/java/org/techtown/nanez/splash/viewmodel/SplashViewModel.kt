package org.techtown.nanez.splash.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.techtown.nanez.base.BaseViewModel
import org.techtown.nanez.common.Event
import org.techtown.nanez.common.post
import org.techtown.nanez.domain.usecase.UserLoginInfoUseCase
import org.techtown.nanez.splash.data.SplashEventData
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/14
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userLoginInfoUseCase: UserLoginInfoUseCase
) : BaseViewModel() {

    private val _eventData by lazy { MutableLiveData<Event<SplashEventData>>() }
    val eventData = _eventData


    fun checkAutoLogin() {
        viewModelScope.launch {
            delay(2000)
            userLoginInfoUseCase.getUserLoginInfo().collect { result ->
                if (result.email.isEmpty() || result.passWord.isEmpty()) {
                    _eventData.post(Event(SplashEventData.MoveToLoginPage))
                } else {
                    _eventData.post(Event(SplashEventData.MoveToMainPage))
                }
            }
        }
    }
}