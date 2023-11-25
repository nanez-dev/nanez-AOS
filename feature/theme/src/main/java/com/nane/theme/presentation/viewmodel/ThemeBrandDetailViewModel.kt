package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.domain.mapper.BrandDetailDomainMapper
import com.nane.theme.domain.usecase.BrandDetailUsecase
import com.nane.theme.presentation.data.BrandDetailViewData
import com.nane.theme.presentation.data.BrandPerfumeViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.NaneLog
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeBrandDetailViewModel @Inject constructor(
    private val brandDetailUsecase: BrandDetailUsecase,
    private val mapper: BrandDetailDomainMapper
) : BaseViewModel() {

    private val _brandDetailData by lazy { MutableLiveData<BrandDetailViewData>() }
    val brandDetailData: LiveData<BrandDetailViewData>
        get() = _brandDetailData

//    private val _relativePerfumes by lazy { MutableLiveData<List<BrandPerfumeViewData>>() }
//    val relativePerfumes: LiveData<List<BrandPerfumeViewData>>
//        get() = _relativePerfumes

    fun getBrandDetailData(brandId: Int, limit: Int) {
        viewModelScope.launch {
            brandDetailUsecase.getBrandDetail(brandId = brandId, limit = limit).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _brandDetailData.post(mapper.toViewData(result.data))
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