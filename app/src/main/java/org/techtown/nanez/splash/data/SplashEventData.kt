package org.techtown.nanez.splash.data

/**
 * Created by iseungjun on 2023/08/17
 */
sealed class SplashEventData {
    object MoveToMainPage : SplashEventData()
    object MoveToLoginPage : SplashEventData()
}
