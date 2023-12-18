package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.presentation.mapper.BrandDomainMapper
import com.nane.theme.domain.usecase.BrandsUsecase
import com.nane.theme.presentation.data.BrandItemViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeBrandViewModel @Inject constructor(
    private val brandsUsecase: BrandsUsecase,
    private val mapper: BrandDomainMapper
) : BaseViewModel() {

    private val _popularBrandsViewDataList by lazy{ MutableLiveData<List<BrandItemViewData>>() }
    val popularBrandsViewDataList: LiveData<List<BrandItemViewData>>
        get() = _popularBrandsViewDataList

    private val _allBrandsViewDataList by lazy{ MutableLiveData<List<BrandItemViewData>>() }
    val allBrandsViewDataList: LiveData<List<BrandItemViewData>>
        get() = _allBrandsViewDataList

    fun getBrandViewData() {
        viewModelScope.launch {
            brandsUsecase.getAllBrands().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        val viewData = mapper.toViewData(result.data)
                        _popularBrandsViewDataList.post(viewData.popularBrands)
                        _allBrandsViewDataList.post(viewData.allBrands)
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