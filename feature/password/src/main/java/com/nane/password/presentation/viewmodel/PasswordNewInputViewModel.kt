package com.nane.password.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.R
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.password.domain.usecase.PasswordNewInputUseCase
import com.nane.password.presentation.data.PasswordNewInputEventData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by haul on 3/24/24
 */
@HiltViewModel
class PasswordNewInputViewModel @Inject constructor(
    private val useCase: PasswordNewInputUseCase
) : BaseViewModel() {


    private val _eventData by lazy { MutableLiveData<Event<PasswordNewInputEventData>>() }
    val eventData: LiveData<Event<PasswordNewInputEventData>> get() = _eventData


    fun changeMyPassword(currentPassword: String, newPassword: String) {
        viewModelScope.launch {
            showLoading(true)
            useCase.changeMyPassword(currentPassword, newPassword).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _eventData.post(Event(PasswordNewInputEventData.CompleteChange))
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

    fun checkSamePassword(password: String, passwordCheck: String) {
        val result = useCase.checkSamePassword(password, passwordCheck)
        if (!result) {
            _eventData.post(Event(PasswordNewInputEventData.ShowErrorView(ResUtils.instance.getString(R.string.msg_error_not_same_password))))
        } else {
            checkPasswordPatten(password)
        }
    }

    private fun checkPasswordPatten(password: String) {
        val result = useCase.checkPasswordPatten(password)
        if (!result) {
            _eventData.post(Event(PasswordNewInputEventData.ShowErrorView(ResUtils.instance.getString(com.nane.base.R.string.msg_error_not_patten_password))))
        } else {
            _eventData.post(Event(PasswordNewInputEventData.ShowErrorView(null)))
        }
        _eventData.post(Event(PasswordNewInputEventData.EnableNextBtn(result)))
    }
}