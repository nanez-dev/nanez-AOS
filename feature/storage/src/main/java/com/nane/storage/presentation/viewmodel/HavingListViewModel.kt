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

@HiltViewModel
class HavingListViewModel @Inject constructor(
    private val storageUseCase: StorageUseCase
) : BaseViewModel() {

    private val _havingList by lazy { MutableLiveData<List<StorageViewData.StorageItem>>() }
    val havingList: LiveData<List<StorageViewData.StorageItem>> = _havingList

    fun getMyList(type: String?) {
        viewModelScope.launch {
            showLoading(true)

            when (val domainResult = storageUseCase.getMyList(type)) {
                is DomainResult.Success -> {
                    _havingList.postValue(domainResult.data)
                }
                is DomainResult.Failed -> {
                    _havingList.postValue(emptyList())
                }
                is DomainResult.Error -> {
                    _havingList.postValue(emptyList())
                }
            }

            showLoading(false)
        }
    }
}