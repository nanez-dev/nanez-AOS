package com.nane.join.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.join.domain.data.JoinSignUpDTO
import com.nane.join.domain.mapper.JoinDomainMapper
import com.nane.join.domain.repo.IJoinRepository
import com.nane.join.presentation.data.JoinAccordViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * Created by haul on 10/30/23
 */
class JoinUseCase @Inject constructor(
    private val repository: IJoinRepository,
    private val mapper: JoinDomainMapper,
) {

    private val passwordPattern by lazy { "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,20}\$" }

    suspend fun postSignUp(signUpDTO: JoinSignUpDTO): Flow<DomainResult<Boolean>> = flow {
        repository.postSignUp(signUpDTO).collect { result ->
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

    fun checkSamePassword(password: String, passwordCheck: String) = password == passwordCheck

    fun checkPasswordPatten(password: String) = Pattern.compile(passwordPattern).matcher(password).matches()

    suspend fun checkNickNameVerify(nickName: String): Flow<DomainResult<Boolean>> = flow {
        repository.postCheckNickNameVerify(nickName).collect { result ->
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

    suspend fun checkEventCodeVerify(code: String): Flow<DomainResult<Boolean>> = flow {
        repository.postCheckEventCodeVerify(code).collect { result ->
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

    suspend fun getAllAccordList(): Flow<DomainResult<List<JoinAccordViewData>>> = flow {
        repository.getAllAccordList().collect {  result ->
            when (result) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(result.data.map { mapper.toAccordViewData(it) }))
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