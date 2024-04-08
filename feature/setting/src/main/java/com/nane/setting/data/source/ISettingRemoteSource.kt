package com.nane.setting.data.source

import retrofit2.Response

interface ISettingRemoteSource {
    suspend fun withdraw(): Response<Boolean>
}