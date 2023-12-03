package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.presentation.mapper.AccordDomainMapper
import com.nane.theme.domain.usecase.AllAccordsUsecase
import com.nane.theme.presentation.data.AccordItemViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeAccordViewModel @Inject constructor(
    private val allAccordsUsecase: AllAccordsUsecase,
    private val mapper: AccordDomainMapper
) : BaseViewModel() {

    private val _popularAccordItemViewDataList by lazy{ MutableLiveData<List<AccordItemViewData>>() }
    val popularAccordItemViewDataList: LiveData<List<AccordItemViewData>>
        get() = _popularAccordItemViewDataList

    private val _allAccordItemViewDataList by lazy{ MutableLiveData<List<AccordItemViewData>>() }
    val allAccordItemViewDataList: LiveData<List<AccordItemViewData>>
        get() = _allAccordItemViewDataList

    fun getAccordViewData() {
        viewModelScope.launch {
            allAccordsUsecase.getAllAccords().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _popularAccordItemViewDataList.post(mapper.toViewData(result.data).popularAccords)
                        _allAccordItemViewDataList.post(mapper.toViewData(result.data).allAccords)
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