package org.techtown.nanez.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.techtown.nanez.data.repository.UserRepository
import org.techtown.nanez.domain.data.UserLoginDomainDTO
import org.techtown.nanez.domain.mapper.UserDomainMapper
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserLoginInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userDomainMapper: UserDomainMapper,
) {

    suspend fun getUserLoginInfo(): Flow<UserLoginDomainDTO> = flow {
        userRepository.getUserLoginInfo()
            .map { dto ->
                userDomainMapper.toDomainDTO(dto)
            }
            .collect { domainDto ->
                emit(domainDto)
            }
    }.catch {
        emit(UserLoginDomainDTO("", ""))
    }
}