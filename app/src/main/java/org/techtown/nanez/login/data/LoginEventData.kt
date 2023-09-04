package org.techtown.nanez.login.data

/**
 * Created by iseungjun on 2023/09/04
 */
sealed class LoginEventData {

    object LoginSuccess : LoginEventData()
    object NotMatchPassword : LoginEventData()
    object NotMatchEmail : LoginEventData()

    object NotInputEmail : LoginEventData()
    object NotInputPassword : LoginEventData()
}
