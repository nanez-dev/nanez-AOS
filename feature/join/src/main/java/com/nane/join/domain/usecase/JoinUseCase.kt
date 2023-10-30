package com.nane.join.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.join.domain.repo.IJoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by haul on 10/30/23
 */
class JoinUseCase @Inject constructor(
    private val repository: IJoinRepository
) {

    suspend fun postSendAuthEmail(email: String): Flow<DomainResult<Boolean>> = flow {
        repository.postSendAuthEmail(email).collect { result ->
            when (result) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(result.data))
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(result.msg, result.code))
                }
                is DataResult.Error -> {
                    emit(DomainResult.Error(result.exception))
                }
            }
        }
    }

    suspend fun checkAuthEmailCode(code: String, email: String): Flow<DomainResult<Boolean>> = flow {
        repository.postCheckAuthEmailCode(code, email).collect { result ->
            when (result) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(result.data))
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(result.msg, result.code))
                }
                is DataResult.Error -> {
                    emit(DomainResult.Error(result.exception))
                }
            }
        }
    }
}