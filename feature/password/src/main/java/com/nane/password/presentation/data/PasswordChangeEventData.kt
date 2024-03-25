package com.nane.password.presentation.data

/**
 * Created by haul on 3/24/24
 */
sealed class PasswordActEventData {
    object MoveToInputNewPassword : PasswordActEventData()
    object MoveToCompleteChange : PasswordActEventData()
}

sealed class PasswordNewInputEventData {
    data class ShowErrorView(val msg: String?) : PasswordNewInputEventData()
    data class EnableNextBtn(val isEnable: Boolean) : PasswordNewInputEventData()
    object CompleteChange : PasswordNewInputEventData()
}