package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.domain.usecase.BrandDetailUsecase
import com.nane.theme.presentation.data.BrandItemViewData
import com.nane.theme.presentation.data.PerfumeViewData
import com.nane.theme.presentation.mapper.BrandDetailDomainMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeBrandDetailViewModel @Inject constructor(
    private val brandDetailUsecase: BrandDetailUsecase,
    private val mapper: BrandDetailDomainMapper
) : BaseViewModel() {

    private val _brandItem by lazy { MutableLiveData<BrandItemViewData>() }
    val brandItem: LiveData<BrandItemViewData>
        get() = _brandItem

    private val _relatedPerfumes by lazy { MutableLiveData<List<PerfumeViewData>>() }
    val relatedPerfumes: LiveData<List<PerfumeViewData>>
        get() = _relatedPerfumes

    fun getBrandDetailData(brandId: Int, limit: Int) {
        viewModelScope.launch {
            showLoading(true)
            brandDetailUsecase.getBrandDetail(brandId = brandId).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        val viewData = mapper.toViewData(result.data)
                        _brandItem.post(viewData.brandItemViewData)
                        _relatedPerfumes.post(viewData.relatedPerfumes)
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