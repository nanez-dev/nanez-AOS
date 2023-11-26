package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.domain.mapper.AccordDetailDomainMapper
import com.nane.theme.domain.usecase.AccordDetailUsecase
import com.nane.theme.presentation.data.AccordDetailViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.NaneLog
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeAccordDetailViewModel @Inject constructor(
    private val accordDetailUsecase: AccordDetailUsecase,
    private val mapper: AccordDetailDomainMapper
) : BaseViewModel() {

    private val _accordDetailData by lazy { MutableLiveData<AccordDetailViewData>() }
    val accordDetailData: LiveData<AccordDetailViewData>
        get() = _accordDetailData

//    private val _relativePerfumes by lazy { MutableLiveData<List<BrandPerfumeViewData>>() }
//    val relativePerfumes: LiveData<List<BrandPerfumeViewData>>
//        get() = _relativePerfumes

    fun getAccordDetail(id: Int) {
        viewModelScope.launch {
            accordDetailUsecase.getAccordDetail(id = id).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _accordDetailData.post(mapper.toViewData(result.data))
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