package org.techtown.nanez.data.data.user

/**
 * Created by iseungjun on 2023/08/17
 */
data class UserLoginDTO(
    val userEmail: String?,
    val userPassword: String?,
    val accessToken: String?,
    val refreshToken: String?
)