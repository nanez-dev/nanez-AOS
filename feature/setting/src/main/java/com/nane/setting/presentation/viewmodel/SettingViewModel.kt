package com.nane.setting.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.setting.domain.usecase.UserUsecase
import com.nane.setting.presentation.data.SettingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val userUsecase: UserUsecase
): BaseViewModel() {

    private val _eventData by lazy { MutableLiveData<Event<SettingEvent>>() }
    val eventData: LiveData<Event<SettingEvent>> get() = _eventData

    fun logOut() {
        viewModelScope.launch {
            showLoading(true)
            userUsecase.logOut().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _eventData.post(Event(SettingEvent.LogoutEvent))
                    }
                    is DomainResult.Failed -> {
                        showErrorToast(result.msg)
                    }
                    is DomainResult.Error -> {
                        showErrorToast()
                    }
                }
            }
            showLoading(false)
        }
    }

    fun withdraw() {
        viewModelScope.launch {
            showLoading(true)
            userUsecase.withdraw().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _eventData.post(Event(SettingEvent.LogoutEvent))
                    }
                    is DomainResult.Failed -> {
                        showErrorToast(result.msg)
                    }
                    is DomainResult.Error -> {
                        showErrorToast()
                    }
                }
            }
            showLoading(false)
        }
    }
}