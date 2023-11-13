package com.nane.storage.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.storage.domain.usecase.WishListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by haul on 11/4/23
 */
class WishListViewModel @Inject constructor(
    private val useCase: WishListUseCase
) : BaseViewModel() {

    private val _wishList by lazy { MutableLiveData<String>() }
    val wishList: LiveData<String> = _wishList


    fun getMyList(type: String?) {
        viewModelScope.launch {
            when (val domainResult = useCase.getMyList(type)) {
                is DomainResult.Success -> {
                    _wishList.postValue(domainResult.data)
                }
                is DomainResult.Failed -> {}
                is DomainResult.Error -> {}
            }
        }
    }

}