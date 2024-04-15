package com.nane.setting.presentation.data

sealed class SettingEvent {
    object LogoutEvent: SettingEvent()
}