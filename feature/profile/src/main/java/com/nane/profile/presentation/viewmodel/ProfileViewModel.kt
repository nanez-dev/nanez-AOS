package com.nane.profile.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.profile.domain.usecase.ProfileUseCase
import com.nane.profile.presentation.data.ProfileLoginViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

    private var myWishCount = 0
    private var myHavingCount = 0

    fun getProfileInfo() {
        viewModelScope.launch {
            showLoading(true)
            listOf(
                async { getMyWishItemCount() },
                async { getMyHavingItemCount() }
            ).awaitAll()

            useCase.getProfileInfo().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        result.data.havingCount = myHavingCount
                        result.data.wishCount = myWishCount
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
            showLoading(false)
        }
    }


    private suspend fun getMyWishItemCount() {
        myWishCount = when (val result = useCase.getMyWishCountInfo()) {
            is DomainResult.Success -> {
                result.data
            }
            else -> 0
        }
    }

    private suspend fun getMyHavingItemCount() {
        myHavingCount = when (val result = useCase.getMyHavingCountInfo()) {
            is DomainResult.Success -> {
                result.data
            }
            else -> 0
        }
    }
}