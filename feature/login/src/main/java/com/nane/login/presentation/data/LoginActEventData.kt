package com.nane.login.presentation.data

/**
 * Created by iseungjun on 2023/10/15
 */
sealed class LoginActEventData {

    object StartGuestMode : LoginActEventData()
    object MoveEmailLogin : LoginActEventData()
}
