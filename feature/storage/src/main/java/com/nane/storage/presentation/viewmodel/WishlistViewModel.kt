package com.nane.storage.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.network.api.storage.StorageApi
import com.nane.storage.domain.usecase.WishListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishListUseCase: WishListUseCase
) : BaseViewModel() {
    private val _wishList by lazy { MutableLiveData<StorageApi.Response>() }
    val wishList: LiveData<StorageApi.Response> = _wishList

    fun getMyList(type: String?) {
        viewModelScope.launch {
            when (val domainResult = wishListUseCase.getMyList(type)) {
                is DomainResult.Success -> {
                    _wishList.postValue(domainResult.data)
                }
                is DomainResult.Failed -> {}
                is DomainResult.Error -> {}
            }
        }
    }
}