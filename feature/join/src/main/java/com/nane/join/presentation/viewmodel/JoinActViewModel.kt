package com.nane.join.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.join.domain.usecase.JoinUseCase
import com.nane.join.presentation.data.JoinActEventData
import com.nane.join.presentation.data.JoinUserViewData
import com.nane.join.domain.mapper.JoinDomainMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.NaneLog
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.post
import javax.inject.Inject
import kotlin.math.min
import kotlin.math.max

/**
 * Created by haul on 10/28/23
 */
@HiltViewModel
class JoinActViewModel @Inject constructor(
    private val useCase: JoinUseCase,
    private val mapper: JoinDomainMapper
) : BaseViewModel() {

    private var totalProgress = 15
    private val joinUserViewData by lazy { JoinUserViewData() }

    private val _eventData by lazy { MutableLiveData<Event<JoinActEventData>>() }
    val eventData: LiveData<Event<JoinActEventData>> = _eventData

    fun updateMarketingAgree(isAgree: Boolean) {
        joinUserViewData.isAccepted = isAgree
        postNextStep()
    }

    fun updateEmail(email: String) {
        joinUserViewData.email = email
        postNextStep()
    }

    fun updateNickName(nickName: String) {
        joinUserViewData.nickName = nickName
        postNextStep()
    }

    fun updatePassword(password: String) {
        joinUserViewData.password = password
        postNextStep()
    }

    fun updateAccordId(targetId: Int) {
        joinUserViewData.accordId = targetId
        totalProgress = 100
        postNextStep()
    }

    fun updateEventCode(code: String?) {
        joinUserViewData.referCode = code
    }

    private fun postNextStep() {
        NaneLog.d("joinUserViewData > $joinUserViewData")
        totalProgress = min(totalProgress + 15, 100)
        _eventData.post(Event(JoinActEventData.ChangeProgressView(totalProgress)))
        _eventData.post(Event(JoinActEventData.MoveNextStep))
    }

    fun postPreStep() {
        totalProgress = max(totalProgress - 15, 0)
        _eventData.post(Event(JoinActEventData.ChangeProgressView(totalProgress)))
    }


    fun postSignUp() {
        NaneLog.d("joinUserViewData postSignUp > $joinUserViewData")
        viewModelScope.launch {
            useCase.postSignUp(mapper.toSignUpDto(joinUserViewData)).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _eventData.post(Event(JoinActEventData.SuccessSignUp))
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