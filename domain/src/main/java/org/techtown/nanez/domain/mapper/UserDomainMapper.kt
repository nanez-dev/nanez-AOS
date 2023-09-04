package org.techtown.nanez.domain.mapper

import org.techtown.nanez.data.api.users.SignInApi
import org.techtown.nanez.data.data.user.UserLoginDTO
import org.techtown.nanez.domain.data.UserLoginDomainDTO
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserDomainMapper @Inject constructor(){

    fun toDataDTO(domain: UserLoginDomainDTO): UserLoginDTO {
        return UserLoginDTO(
            userEmail = domain.email,
            userPassword = domain.passWord,
            accessToken = domain.accessToken,
            refreshToken = domain.refreshToken
        )
    }

    fun toDomainDTO(dto: UserLoginDTO): UserLoginDomainDTO {
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