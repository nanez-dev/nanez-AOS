package com.nane.login.data.mapper

import com.nane.login.data.data.UserLoginData
import com.nane.login.domain.data.UserLoginDomainDTO
import org.techtown.nanez.data.api.users.SignInApi
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserDataMapper @Inject constructor(){

    fun toData(domain: UserLoginDomainDTO): UserLoginData {
        return UserLoginData(
            userEmail = domain.email,
            userPassword = domain.passWord,
            accessToken = domain.accessToken,
            refreshToken = domain.refreshToken
        )
    }

    fun toDomainDTO(dto: UserLoginData): UserLoginDomainDTO {
        return UserLoginDomainDTO(
            email = dto.userEmail,
            passWord = dto.userPassword,
            accessToken = dto.accessToken,
            refreshToken = dto.refreshToken
        )
    }

    fun toDomainDTO(email: String, password: String, apiResult: SignInApi.Response?): UserLoginDomainDTO {
        return UserLoginDomainDTO(
            email = email,
            passWord = password,
            accessToken = apiResult?.access_token,
            refreshToken = apiResult?.refresh_token
        )
    }
}