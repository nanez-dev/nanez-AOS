package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.domain.mapper.BrandDomainMapper
import com.nane.theme.domain.usecase.AllBrandsUsecase
import com.nane.theme.domain.usecase.PopularBrandsUsecase
import com.nane.theme.presentation.data.BrandViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeBrandViewModel @Inject constructor(
    private val popularBrandsUsecase: PopularBrandsUsecase,
    private val allBrandsUsecase: AllBrandsUsecase,
    private val mapper: BrandDomainMapper
) : BaseViewModel() {

    private val _popularBrandsViewDataList by lazy{ MutableLiveData<List<BrandViewData>>() }
    val popularBrandsViewDataList: LiveData<List<BrandViewData>>
        get() = _popularBrandsViewDataList

    private val _allBrandsViewDataList by lazy{ MutableLiveData<List<BrandViewData>>() }
    val allBrandsViewDataList: LiveData<List<BrandViewData>>
        get() = _allBrandsViewDataList

    fun getPopularBrandViewData() {
        viewModelScope.launch {
            popularBrandsUsecase.getPopularBrands().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _popularBrandsViewDataList.post(mapper.toViewData(result.data))
                    }
                    is DomainResult.Failed -> {

                    }
                    is DomainResult.Error -> {

                    }
                }
            }
        }
    }

    fun getAllBrandViewData() {
        viewModelScope.launch {
            allBrandsUsecase.getAllBrands().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _allBrandsViewDataList.post(mapper.toViewData(result.data))
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