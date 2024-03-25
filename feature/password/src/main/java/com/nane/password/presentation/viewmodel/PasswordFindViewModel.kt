package com.nane.password.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.password.domain.usecase.PasswordFindUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by haul on 3/25/24
 */
@HiltViewModel
class PasswordFindViewModel @Inject constructor(
    private val useCase: PasswordFindUseCase,
) : BaseViewModel() {

    private val _passwordSendResult by lazy { MutableLiveData<Boolean>() }
    val passwordSendResult: LiveData<Boolean> get() = _passwordSendResult


    fun sendPasswordEmail(email: String) {
        viewModelScope.launch {
            showLoading(true)
            when (val result = useCase.sendPasswordEmail(email)) {
                is DomainResult.Success -> {
                    _passwordSendResult.post(result.data)
                }
                is DomainResult.Failed -> {
                    _passwordSendResult.post(false)
                }
                is DomainResult.Error -> {
                    _passwordSendResult.post(false)
                    showErrorToast()
                }
            }
            showLoading(false)
        }
    }
}