package com.nane.profile.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.profile.domain.usecase.ProfileUseCase
import com.nane.profile.presentation.data.ProfileLoginViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by haul on 3/2/24
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCase: ProfileUseCase,
) : BaseViewModel() {

    private val _profileViewData by lazy { MutableLiveData<ProfileLoginViewData>() }
    val profileViewData get() = _profileViewData

    fun getProfileInfo() {
        viewModelScope.launch {
            useCase.getProfileInfo().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _profileViewData.post(result.data)
                    }
                    is DomainResult.Failed -> {
                        showErrorToast(result.msg)
                    }
                    is DomainResult.Error -> {
                        showErrorToast()
                    }
                }
             }
        }
    }

    fun changeMyPassword(current_password: String, new_password: String) {
        viewModelScope.launch {
            useCase.changeMyPassword(current_password, new_password).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        // 성공일 때 처리 필요
                    }
                    is DomainResult.Failed -> {
                    }
                    is DomainResult.Error -> {
                    }
                }
            }
        }
    }

}