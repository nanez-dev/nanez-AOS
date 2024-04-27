package com.nane.setting.presentation.data

sealed class RegistrationEvent {
    object MoveNextStep : RegistrationEvent()
    object MovePreStep : RegistrationEvent()
}