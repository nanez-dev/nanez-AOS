package org.techtown.nanez.userinfo.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.techtown.nanez.data.api.users.SignInApi
import org.techtown.nanez.userinfo.data.repository.UserRepository
import org.techtown.nanez.userinfo.domain.data.UserLoginDomainDTO
import org.techtown.nanez.userinfo.data.mapper.UserDataMapper
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserLoginInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userDataMapper: UserDataMapper,
) {

    suspend fun getUserLoginInfo(): Flow<DomainResult<UserLoginDomainDTO>> = flow {
        userRepository.getUserLoginInfo().collect { response ->
            when (response) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(userDataMapper.toDomainDTO(response.data)))
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(response.msg))
                }
                is DataResult.Error -> {
                    emit(DomainResult.Error(response.exception))
                }
            }
        }
    }


    suspend fun requestLogin(email: String, password: String): Flow<DomainResult<UserLoginDomainDTO>> = flow {
        userRepository.postLogin(SignInApi.Body(email = email, password = password)).collect { response ->
            when (response) {
                is DataResult.Success -> {
                    val domainDto = userDataMapper.toDomainDTO(email, password, response.data)
                    saveLoginInfo(domainDto)
                    emit(DomainResult.Success(domainDto))
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(response.msg))
                }
                is DataResult.Error -> {
                    emit(DomainResult.Error(response.exception))
                }
            }
        }
    }

    private suspend fun saveLoginInfo(domainDTO: UserLoginDomainDTO) {
        userRepository.saveLoginInfo(userDataMapper.toDataDTO(domainDTO))
    }
}