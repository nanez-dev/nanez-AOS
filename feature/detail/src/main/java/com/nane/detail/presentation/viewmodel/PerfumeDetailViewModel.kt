package com.nane.detail.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.detail.domain.usecase.PerfumeDetailUseCase
import com.nane.detail.presentation.data.PerfumeDetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by haul on 3/10/24
 */
@HiltViewModel
class PerfumeDetailViewModel @Inject constructor(
    private val useCase: PerfumeDetailUseCase,
) : BaseViewModel() {

    private val _eventData by lazy { MutableLiveData<Event<PerfumeDetailEvent>>() }
    val eventData get() = _eventData


    fun getPerfumeDetailInfo(targetId: Int) {
        viewModelScope.launch {
            showLoading(true)

            when (val result = useCase.getPerfumeDetail(targetId)) {
                is DomainResult.Success -> {
                    _eventData.post(Event(PerfumeDetailEvent.InitView(result.data)))
                }
                is DomainResult.Failed -> {
                    showErrorToast(result.msg)
                    _eventData.post(Event(PerfumeDetailEvent.Finish))
                }
                is DomainResult.Error -> {
                    showErrorToast()
                    _eventData.post(Event(PerfumeDetailEvent.Finish))
                }
            }

            showLoading(false)
        }
    }

    fun onClickWish(perfumeId: Int) {
        viewModelScope.launch {
            showLoading(true)

            when (val result = useCase.patchPerfumeWish(perfumeId)) {
                is DomainResult.Success -> {
                    _eventData.post(Event(PerfumeDetailEvent.RefreshWish(result.data)))
                }
                is DomainResult.Failed -> {
                    showErrorToast(result.msg)
                    _eventData.post(Event(PerfumeDetailEvent.Finish))
                }
                is DomainResult.Error -> {
                    showErrorToast()
                    _eventData.post(Event(PerfumeDetailEvent.Finish))
                }
            }

            showLoading(false)
        }
    }

    fun onClickHaving(perfumeId: Int) {
        viewModelScope.launch {
            showLoading(true)

            when (val result = useCase.patchPerfumeHaving(perfumeId)) {
                is DomainResult.Success -> {
                    _eventData.post(Event(PerfumeDetailEvent.RefreshHaving(result.data)))
                }
                is DomainResult.Failed -> {
                    showErrorToast(result.msg)
                    _eventData.post(Event(PerfumeDetailEvent.Finish))
                }
                is DomainResult.Error -> {
                    showErrorToast()
                    _eventData.post(Event(PerfumeDetailEvent.Finish))
                }
            }

            showLoading(false)
        }

    }
}