package com.nane.setting.domain.repository

import com.nane.base.data.DataResult
import kotlinx.coroutines.flow.Flow

interface ISettingRepository {
    suspend fun logOut(): Flow<DataResult<Unit>>
    suspend fun withdraw(): Flow<DataResult<Unit>>
}