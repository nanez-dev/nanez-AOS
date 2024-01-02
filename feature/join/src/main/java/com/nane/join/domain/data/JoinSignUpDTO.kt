package com.nane.join.domain.data

/**
 * Created by haul on 11/30/23
 */
data class JoinSignUpDTO(
    val nickname: String?,
    val email: String?,
    val password: String?,
    val referCode: String?,
    val accordId: Int,
    val isAccepted: Boolean,
)
