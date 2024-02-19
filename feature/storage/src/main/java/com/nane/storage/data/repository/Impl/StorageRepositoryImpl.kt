package com.nane.storage.data.repository.Impl

import com.nane.base.data.DataResult
import com.nane.network.api.storage.StorageApi
import com.nane.network.parser.getParseErrorResult
import com.nane.storage.data.repository.IStorageRepository
import com.nane.storage.data.source.IStorageRemoteSource
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val remoteSource: IStorageRemoteSource
) : IStorageRepository {
    override suspend fun getMyList(type: String?): DataResult<List<StorageApi.Response>?> {
        val response = remoteSource.getMyList(type)
        return if (response.isSuccessful) {
            DataResult.Success(response.body())
        } else {
            val failed = getParseErrorResult(response)
            DataResult.Failed(failed.errorMsg, failed.errorCode)
        }
    }
}