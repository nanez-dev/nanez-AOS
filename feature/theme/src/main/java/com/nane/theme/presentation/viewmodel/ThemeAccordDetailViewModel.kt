package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.presentation.mapper.AccordDetailDomainMapper
import com.nane.theme.domain.usecase.AccordDetailUsecase
import com.nane.theme.presentation.data.AccordDetailViewData
import com.nane.theme.presentation.data.AccordItemViewData
import com.nane.theme.presentation.data.AccordPerfumeViewData
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

    private val _accordItem by lazy { MutableLiveData<AccordItemViewData>() }
    val accordItem: LiveData<AccordItemViewData>
        get() = _accordItem

    private val _relatedPerfumes by lazy { MutableLiveData<List<AccordPerfumeViewData>>() }
    val relatedPerfume: LiveData<List<AccordPerfumeViewData>>
        get() = _relatedPerfumes

    fun getAccordDetail(id: Int) {
        viewModelScope.launch {
            accordDetailUsecase.getAccordDetail(id = id).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _accordItem.post(mapper.toViewData(result.data).accordItemViewData)
                        _relatedPerfumes.post(mapper.toViewData(result.data).relatedPerfumes)
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