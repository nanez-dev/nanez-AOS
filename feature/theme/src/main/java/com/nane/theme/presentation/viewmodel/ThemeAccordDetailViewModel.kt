package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.domain.usecase.AccordDetailUsecase
import com.nane.theme.presentation.data.AccordItemViewData
import com.nane.theme.presentation.data.PerfumeViewData
import com.nane.theme.presentation.mapper.AccordDetailDomainMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeAccordDetailViewModel @Inject constructor(
    private val accordDetailUsecase: AccordDetailUsecase,
    private val mapper: AccordDetailDomainMapper
) : BaseViewModel() {

    private val _accordItem by lazy { MutableLiveData<AccordItemViewData>() }
    val accordItem: LiveData<AccordItemViewData>
        get() = _accordItem

    private val _relatedPerfumes by lazy { MutableLiveData<List<PerfumeViewData>>() }
    val relatedPerfume: LiveData<List<PerfumeViewData>>
        get() = _relatedPerfumes

    fun getAccordDetail(id: Int) {
        viewModelScope.launch {
            accordDetailUsecase.getAccordDetail(id = id).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        val viewData = mapper.toViewData(result.data)
                        _accordItem.post(viewData.accordItemViewData)
                        _relatedPerfumes.post(viewData.relatedPerfumes)
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