package com.nane.login.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.login.data.mapper.UserDataMapper
import com.nane.login.data.repository.IUserRepository
import com.nane.login.data.repository.impl.UserRepository
import com.nane.login.domain.data.UserLoginDomainDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.techtown.nanez.data.api.users.SignInApi
import org.techtown.nanez.utils.NaneLog
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserLoginInfoUseCase @Inject constructor(
    private val userRepository: IUserRepository,
) {

    suspend fun getUserLoginInfo(): Flow<DomainResult<UserLoginDomainDTO>> = flow {
        userRepository.getUserLoginInfo().collect { response ->
            when (response) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(response.data))
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(response.msg, response.code))
                }
                is DataResult.Error -> {
                    NaneLog.e(response.exception)
                    emit(DomainResult.Error(response.exception))
                }
            }
        }
    }


    suspend fun requestLogin(email: String, password: String): Flow<DomainResult<UserLoginDomainDTO>> = flow {
        userRepository.postLogin(SignInApi.Body(email = email, password = password)).collect { response ->
            when (response) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(response.data))
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(response.msg, response.code))
                }
                is DataResult.Error -> {
                    NaneLog.e(response.exception)
                    emit(DomainResult.Error(response.exception))
                }
            }
        }
    }

    private suspend fun saveLoginInfo(domainDTO: UserLoginDomainDTO) {
        userRepository.saveLoginInfo(domainDTO)
    }
}