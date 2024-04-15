package com.nane.setting.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.setting.domain.repository.ISettingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingUseCase @Inject constructor(
    private val repository: ISettingRepository
) {
    suspend fun logOut(): Flow<DomainResult<Unit>> = flow {
        repository.logOut().collect {
            emit(DomainResult.Success(Unit))
        }
    }

    suspend fun withdraw(): Flow<DomainResult<Unit>> = flow {
        repository.withdraw().collect { response ->
            when (response) {
                is DataResult.Success -> {
                    repository.logOut().collect {
                        emit(DomainResult.Success(Unit))
                    }
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(response.msg, response.code))
                }
                is DataResult.Error -> {
                    emit(DomainResult.Error(response.exception))
                }
            }
        }
    }
}