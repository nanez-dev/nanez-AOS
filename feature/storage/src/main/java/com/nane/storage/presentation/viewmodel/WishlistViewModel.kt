package com.nane.storage.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.network.api.storage.StorageApi
import com.nane.storage.domain.usecase.WishListUseCase
import com.nane.storage.presentation.data.StorageViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishListUseCase: WishListUseCase
) : BaseViewModel() {
    private val _wishList by lazy { MutableLiveData<List<StorageViewData.StorageItem>>() }
    val wishList: LiveData<List<StorageViewData.StorageItem>> = _wishList

    fun getMyList(type: String?) {
        viewModelScope.launch {
            when (val domainResult = wishListUseCase.getMyList(type)) {
                is DomainResult.Success -> {

                    val storageItem = domainResult.data?.let {
                        StorageViewData.StorageItem.fromApiModel(it)
                    }
                    if (storageItem != null) {
                        _wishList.postValue(listOf(storageItem))
                    }
                }
                is DomainResult.Failed -> {}
                is DomainResult.Error -> {}
            }
        }
    }
}