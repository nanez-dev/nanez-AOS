package com.nane.password.data.repo

import com.nane.base.data.DataResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by haul on 3/25/24
 */
interface IPasswordRepository {
    suspend fun patchChangeMyPassword(currentPassword: String, newPassword: String): Flow<DataResult<Unit>>
    suspend fun logOut(): Flow<DataResult<Unit>>
    suspend fun postRandomPassword(email: String): DataResult<Boolean>
}