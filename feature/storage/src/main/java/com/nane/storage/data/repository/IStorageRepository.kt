package com.nane.storage.data.repository

import com.nane.base.data.DataResult
import com.nane.network.api.storage.StorageApi

interface IStorageRepository {
    suspend fun getMyList(type: String?): DataResult<List<StorageApi.Response>?>
}