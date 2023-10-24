package org.techtown.nanez.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.viewmodel.BaseViewModel
import com.nane.login.domain.usecase.UserLoginInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.techtown.nanez.main.data.MainEventData
import org.techtown.nanez.utils.util.Event
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/14
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userLoginInfoUseCase: UserLoginInfoUseCase
) : BaseViewModel() {

    private val _eventData by lazy { MutableLiveData<Event<MainEventData>>() }
    val eventData = _eventData

    var isLoginCheckDone = false
        private set

    fun checkAutoLogin() {
        viewModelScope.launch {
            delay(2000)
            userLoginInfoUseCase.getUserLoginInfo().collect { result ->
                isLoginCheckDone = true
            }
        }
    }
}