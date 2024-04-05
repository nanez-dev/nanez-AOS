package com.nane.storage.data.source.Impl

import com.nane.network.api.storage.StorageApi
import com.nane.network.service.RetrofitPerfumeService
import com.nane.storage.data.source.IStorageRemoteSource
import retrofit2.Response
import javax.inject.Inject

class StorageRemoteSourceImpl @Inject constructor(
    private val perfumeService: RetrofitPerfumeService
): IStorageRemoteSource {

    override suspend fun getMyList(type: String?): Response<List<StorageApi.Response>> {
        return perfumeService.getMyList(type)
    }
}