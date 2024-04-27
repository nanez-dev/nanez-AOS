package com.nane.setting.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.viewmodel.BaseViewModel
import com.nane.setting.presentation.data.RegistrationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() : BaseViewModel() {

    private val _eventData by lazy { MutableLiveData<Event<RegistrationEvent>>() }
    val eventData: LiveData<Event<RegistrationEvent>> get() = _eventData

    private var brandName = ""

    fun registerBrandName(brandName: String) {
        this.brandName = brandName
        _eventData.post(Event(RegistrationEvent.MoveNextStep))
    }

    fun registerPerfumeName(perfumeName: String) {

        // 제품 등록 요청 API 호출
        viewModelScope.launch {
            _eventData.post(Event(RegistrationEvent.MoveNextStep))
        }
    }

    fun postPreStep() {
        _eventData.post(Event(RegistrationEvent.MovePreStep))
    }
}