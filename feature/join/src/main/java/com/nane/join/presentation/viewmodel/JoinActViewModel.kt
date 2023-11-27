package com.nane.join.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nane.base.viewmodel.BaseViewModel
import com.nane.join.presentation.data.JoinActEventData
import com.nane.join.presentation.data.JoinUserViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.techtown.nanez.utils.util.Event
import org.techtown.nanez.utils.util.post
import javax.inject.Inject
import kotlin.math.min
import kotlin.math.max

/**
 * Created by haul on 10/28/23
 */
@HiltViewModel
class JoinActViewModel @Inject constructor() : BaseViewModel() {

    private var totalProgress = 15
    private val joinUserViewData by lazy { JoinUserViewData() }

    private val _eventData by lazy { MutableLiveData<Event<JoinActEventData>>() }
    val eventData: LiveData<Event<JoinActEventData>> = _eventData

    fun updateAccordId(targetId: Int) {
        joinUserViewData.accordId = targetId
    }

    fun postNextStep() {
        totalProgress = min(totalProgress + 15, 100)
        _eventData.post(Event(JoinActEventData.ChangeProgressView(totalProgress)))
        _eventData.post(Event(JoinActEventData.MoveNextStep))
    }

    fun postPreStep() {
        totalProgress = max(totalProgress - 15, 0)
        _eventData.post(Event(JoinActEventData.ChangeProgressView(totalProgress)))
    }
}