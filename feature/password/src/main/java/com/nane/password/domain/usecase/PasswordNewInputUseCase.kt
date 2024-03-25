package com.nane.password.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.password.data.repo.IPasswordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * Created by haul on 3/24/24
 */
class PasswordNewInputUseCase @Inject constructor(
    private val repository: IPasswordRepository,
) {

    private val passwordPattern by lazy { "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,20}\$" }

    fun checkSamePassword(password: String, passwordCheck: String) = password == passwordCheck

    fun checkPasswordPatten(password: String) = Pattern.compile(passwordPattern).matcher(password).matches()


    suspend fun changeMyPassword(currentPassword: String, newPassword: String): Flow<DomainResult<Unit>> = flow {
        repository.patchChangeMyPassword(currentPassword, newPassword).collect { result ->
            when (result) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(Unit))
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