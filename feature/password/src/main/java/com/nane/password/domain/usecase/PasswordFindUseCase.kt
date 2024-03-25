package com.nane.password.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.password.data.repo.IPasswordRepository
import javax.inject.Inject

/**
 * Created by haul on 3/25/24
 */
class PasswordFindUseCase @Inject constructor(
    private val repository: IPasswordRepository
) {

    suspend fun sendPasswordEmail(email: String): DomainResult<Boolean> {
        return when (val result = repository.postRandomPassword(email)) {
            is DataResult.Success -> {
                DomainResult.Success(result.data)
            }
            is DataResult.Failed -> {
                DomainResult.Failed(result.msg, result.code)
            }
            is DataResult.Error -> {
                DomainResult.Error(result.exception)
            }
        }
    }

}