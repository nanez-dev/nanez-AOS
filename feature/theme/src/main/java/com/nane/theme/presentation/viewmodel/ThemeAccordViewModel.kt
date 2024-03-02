package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.domain.usecase.AccordsUseCase
import com.nane.theme.presentation.data.AccordViewData
import com.nane.theme.presentation.mapper.AccordDomainMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeAccordViewModel @Inject constructor(
    private val accordsUseCase: AccordsUseCase,
) : BaseViewModel() {

    private val _accordItemViewDataList by lazy{ MutableLiveData<List<AccordViewData>>() }
    val accordItemViewDataList: LiveData<List<AccordViewData>>
        get() = _accordItemViewDataList

    fun getAccordViewData() {
        viewModelScope.launch {
            showLoading(true)
            accordsUseCase.getAllAccords().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _accordItemViewDataList.post(result.data)
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