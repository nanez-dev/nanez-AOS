package com.nane.setting.data.source

import com.nane.network.api.users.WithdrawalApi
import retrofit2.Response

interface ISettingRemoteSource {
    suspend fun withdraw(): Response<WithdrawalApi.Body>
}