package com.nane.join.presentation.data

/**
 * Created by haul on 11/6/23
 */
sealed class JoinPasswordEventData {
    data class ShowErrorView(val errorText: String?) : JoinPasswordEventData()
    data class EnableNextBtn(val isEnable: Boolean) : JoinPasswordEventData()
}