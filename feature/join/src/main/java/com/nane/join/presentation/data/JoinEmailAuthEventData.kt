package com.nane.join.presentation.data

/**
 * Created by haul on 10/30/23
 */
sealed class JoinEmailAuthEventData {
    data class SendEmailAuthResult(val isSuccess: Boolean) : JoinEmailAuthEventData()
    data class VerifyCheck(val isSuccessVerify: Boolean) : JoinEmailAuthEventData()
}