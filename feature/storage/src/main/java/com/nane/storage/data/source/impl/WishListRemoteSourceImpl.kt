package com.nane.storage.data.source.impl

import com.nane.network.service.RetrofitPerfumeService
import com.nane.storage.data.source.IWishListRemoteSource
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by haul on 11/4/23
 */
class WishListRemoteSourceImpl @Inject constructor(
    private val perfumeService: RetrofitPerfumeService
) : IWishListRemoteSource {

    override suspend fun getMyList(type: String?): Response<String> {
        return perfumeService.getMyList(type)
    }
}