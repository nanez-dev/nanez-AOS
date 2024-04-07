package com.nane.setting.domain.repository

import com.nane.base.data.DataResult
import com.nane.network.api.users.WithdrawalApi
import kotlinx.coroutines.flow.Flow

interface ISettingRepository {
    suspend fun logOut(): Flow<DataResult<Unit>>
    suspend fun withdraw(): Flow<DataResult<WithdrawalApi.Body?>>
}