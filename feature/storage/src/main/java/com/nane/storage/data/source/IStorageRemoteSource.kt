package com.nane.storage.data.source

import com.nane.network.api.storage.StorageApi
import retrofit2.Response

interface IStorageRemoteSource {
    suspend fun getMyList(type: String?): Response<List<StorageApi.Response>>
}