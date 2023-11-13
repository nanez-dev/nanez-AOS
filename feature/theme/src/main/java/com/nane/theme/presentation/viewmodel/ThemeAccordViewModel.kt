package com.nane.theme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.theme.domain.mapper.AccordDomainMapper
import com.nane.theme.domain.usecase.AllAccordsUsecase
import com.nane.theme.presentation.data.AccordViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class ThemeAccordViewModel @Inject constructor(
    private val allAccordsUsecase: AllAccordsUsecase,
    private val mapper: AccordDomainMapper
) : BaseViewModel() {

    private val _popularAccordsViewDataList by lazy{ MutableLiveData<List<AccordViewData>>() }
    val popularAccordsViewDataList: LiveData<List<AccordViewData>>
        get() = _popularAccordsViewDataList

    private val _allAccordsViewDataList by lazy{ MutableLiveData<List<AccordViewData>>() }
    val allAccordsViewDataList: LiveData<List<AccordViewData>>
        get() = _allAccordsViewDataList

    fun getAccordViewData() {
        viewModelScope.launch {
            // 최근 사랑받는 어코드 가져오기
        }

        // 모든 어코드 가져오기
        viewModelScope.launch {
            allAccordsUsecase.getAllAccords().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _allAccordsViewDataList
                            .post(
                                mapper.toViewData(result.data).sortedWith(
                                    compareBy { it.id }
                                )
                            )
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