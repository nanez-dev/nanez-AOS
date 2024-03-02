package com.nane.login.data.data

/**
 * Created by iseungjun on 2023/08/17
 */
data class UserLoginData(
    val userEmail: String?,
    val userPassword: String?,
    val accessToken: String?,
    val refreshToken: String?
)