package com.nane.storage.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.storage.domain.usecase.StorageUseCase
import com.nane.storage.presentation.data.StorageViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by haul on 2/19/24
 */
@HiltViewModel
class WishListViewModel @Inject constructor(
    private val storageUseCase: StorageUseCase
) : BaseViewModel() {

    private val _wishList by lazy { MutableLiveData<List<StorageViewData.StorageItem>>() }
    val wishList: LiveData<List<StorageViewData.StorageItem>> = _wishList

    fun getMyList(type: String?) {
        viewModelScope.launch {
            showLoading(true)
            when (val domainResult = storageUseCase.getMyList(type)) {
                is DomainResult.Success -> {
                    _wishList.postValue(domainResult.data)
                }
                is DomainResult.Failed -> {
                    _wishList.postValue(emptyList())
                }
                is DomainResult.Error -> {
                    _wishList.postValue(emptyList())
                }
            }
            showLoading(false)
        }
    }
}