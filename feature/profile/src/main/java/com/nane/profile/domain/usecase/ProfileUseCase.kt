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

    suspend fun changeMyPassword(current_password: String, new_password: String): Flow<DataResult<Unit>> = flow {
        repository.patchChangeMyPassword(current_password, new_password).collect { result ->
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