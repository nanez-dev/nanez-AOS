package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.domain.usecase.AccordDetailUseCase
import com.nane.theme.presentation.data.AccordItemViewData
import com.nane.theme.presentation.data.PerfumeViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeAccordDetailViewModel @Inject constructor(
    private val accordDetailUseCase: AccordDetailUseCase,
) : BaseViewModel() {

    private val _accordItem by lazy { MutableLiveData<AccordItemViewData>() }
    val accordItem: LiveData<AccordItemViewData>
        get() = _accordItem

    private val _relatedPerfumes by lazy { MutableLiveData<List<PerfumeViewData>>() }
    val relatedPerfume: LiveData<List<PerfumeViewData>>
        get() = _relatedPerfumes

    fun getAccordDetail(id: Int) {
        viewModelScope.launch {
            showLoading(true)
            accordDetailUseCase.getAccordDetail(id = id).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _accordItem.post(result.data.accordItemViewData)
                        _relatedPerfumes.post(result.data.relatedPerfumes)
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