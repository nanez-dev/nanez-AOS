package com.nane.profile.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.profile.data.repo.IProfileRepository
import com.nane.profile.domain.mapper.ProfileDomainMapper
import com.nane.profile.presentation.data.ProfileLoginViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by haul on 3/2/24
 */
class ProfileUseCase @Inject constructor(
    private val repository: IProfileRepository,
    private val mapper: ProfileDomainMapper,
) {

    suspend fun getProfileInfo(): Flow<DomainResult<ProfileLoginViewData>> = flow {
        repository.getMyProfile().collect { result ->
            when (result) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(mapper.toViewData(result.data)))
                }
                is DataResult.Error -> {
                    emit(DomainResult.Error(result.exception))
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(result.msg, result.code))
                }
            }
        }
    }

    suspend fun getMyWishCountInfo(): DomainResult<Int> {
        return when (val result = repository.getMyList(TYPE_WISH)) {
            is DataResult.Success -> {
                DomainResult.Success(result.data)
            }
            is DataResult.Error -> {
                DomainResult.Error(result.exception)
            }
            is DataResult.Failed -> {
                DomainResult.Failed(result.msg, result.code)
            }
        }
    }

    suspend fun getMyHavingCountInfo(): DomainResult<Int> {
        return when (val result = repository.getMyList(TYPE_HAVING)) {
            is DataResult.Success -> {
                DomainResult.Success(result.data)
            }
            is DataResult.Error -> {
                DomainResult.Error(result.exception)
            }
            is DataResult.Failed -> {
                DomainResult.Failed(result.msg, result.code)
            }
        }
    }

    companion object {
        private const val TYPE_WISH = "wish"
        private const val TYPE_HAVING = "having"
    }
}