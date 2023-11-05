package com.nane.storage.data.source.Impl

import com.nane.network.service.RetrofitPerfumeService
import com.nane.storage.data.source.IWishListRemoteSource
import retrofit2.Response
import javax.inject.Inject

class WishListRemoteSourceImpl @Inject constructor(
    private val perfumeService: RetrofitPerfumeService
): IWishListRemoteSource {
    override suspend fun getMyList(type: String?): Response<String> {
        return perfumeService.getMyList(type)
    }
}