package com.nane.profile.data.repo

import com.nane.base.data.DataResult
import com.nane.profile.domain.data.ProfileDTO
import kotlinx.coroutines.flow.Flow

/**
 * Created by haul on 3/2/24
 */
interface IProfileRepository {
    suspend fun getMyProfile(): Flow<DataResult<ProfileDTO>>
    suspend fun patchChangeMyPassword(currentPassword: String, newPassword: String): Flow<DataResult<Unit>>
    suspend fun logOut(): Flow<DataResult<Unit>>
}