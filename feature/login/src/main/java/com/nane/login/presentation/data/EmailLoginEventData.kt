package com.nane.login.presentation.data

/**
 * Created by iseungjun on 2023/09/04
 */
sealed class EmailLoginEventData {
    object LoginSuccess : EmailLoginEventData()
    object NotMatchLoginInfo: EmailLoginEventData()

    object NotInputEmail : EmailLoginEventData()
    object NotInputPassword : EmailLoginEventData()
}
