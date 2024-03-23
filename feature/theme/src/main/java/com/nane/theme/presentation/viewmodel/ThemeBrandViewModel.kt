package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.domain.usecase.BrandUseCase
import com.nane.theme.presentation.data.BrandViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeBrandViewModel @Inject constructor(
    private val brandsUseCase: BrandUseCase,
) : BaseViewModel() {

    private val _brandItemViewDataList by lazy{ MutableLiveData<List<BrandViewData>>() }
    val brandItemViewDataList: LiveData<List<BrandViewData>>
        get() = _brandItemViewDataList

    fun getBrandViewData() {
        viewModelScope.launch {
            showLoading(true)
            brandsUseCase.getAllBrands().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _brandItemViewDataList.post(result.data)
                    }
                    is DomainResult.Failed -> {
                        showErrorToast()
                    }
                    is DomainResult.Error -> {
                        showErrorToast()
                    }
                }
                showLoading(false)
            }
        }
    }
}