package com.nane.storage.data.repository.Impl

import com.nane.base.data.DataResult
import com.nane.network.api.storage.StorageApi
import com.nane.network.parser.getParseErrorResult
import com.nane.storage.data.mapper.StorageDataMapper
import com.nane.storage.data.repository.IStorageRepository
import com.nane.storage.data.source.IStorageRemoteSource
import com.nane.storage.domain.data.StorageDomainDTO
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val remoteSource: IStorageRemoteSource,
    private val mapper: StorageDataMapper,
) : IStorageRepository {

    override suspend fun getMyList(type: String?): DataResult<List<StorageDomainDTO>> {
        val response = remoteSource.getMyList(type)
        return if (response.isSuccessful) {
            DataResult.Success(response.body()?.map { mapper.toDTO(it) } ?: emptyList())
        } else {
            val failed = getParseErrorResult(response)
            DataResult.Failed(failed.errorMsg, failed.errorCode)
        }
    }
}