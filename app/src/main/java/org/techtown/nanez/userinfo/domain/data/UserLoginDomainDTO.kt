package org.techtown.nanez.userinfo.domain.data

/**
 * Created by iseungjun on 2023/08/17
 */
data class UserLoginDomainDTO(
    val email: String?,
    val passWord: String?,
    val accessToken: String?,
    val refreshToken: String?,
)
