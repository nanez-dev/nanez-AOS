package com.nane.join.presentation.data

/**
 * Created by haul on 11/16/23
 */
sealed class JoinNickNameEventData {
    data class VerifyResult(val isDuplicate: Boolean) : JoinNickNameEventData()
}
