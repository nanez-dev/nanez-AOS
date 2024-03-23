package com.nane.login.data.repository

import com.nane.base.data.DataResult
import com.nane.login.domain.data.UserLoginDomainDTO
import kotlinx.coroutines.flow.Flow
import org.techtown.nanez.data.api.users.SignInApi

/**
 * Created by haul on 3/2/24
 */
interface IUserRepository {
    suspend fun getUserLoginInfo(): Flow<DataResult<UserLoginDomainDTO>>
    suspend fun postLogin(body: SignInApi.Body): Flow<DataResult<UserLoginDomainDTO>>
    suspend fun saveLoginInfo(dto: UserLoginDomainDTO)
}