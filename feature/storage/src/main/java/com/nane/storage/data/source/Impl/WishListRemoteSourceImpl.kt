package com.nane.storage.data.source.Impl

import com.nane.network.api.storage.StorageApi
import com.nane.network.service.RetrofitPerfumeService
import com.nane.storage.data.source.IWishListRemoteSource
import retrofit2.Response
import javax.inject.Inject

class WishListRemoteSourceImpl @Inject constructor(
    private val perfumeService: RetrofitPerfumeService
): IWishListRemoteSource {
    override suspend fun getMyList(type: String?): Response<StorageApi.Response> {
        return perfumeService.getMyList(type)
    }
}