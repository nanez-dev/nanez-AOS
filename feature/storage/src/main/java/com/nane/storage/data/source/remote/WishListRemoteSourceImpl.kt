package com.nane.storage.data.source.remote

import com.nane.network.service.RetrofitPerfumeService
import com.nane.storage.data.source.IWishListRemoteSource
import retrofit2.Response
import javax.inject.Inject

// inject 주입 어노테이션
// 생성자
class WishListRemoteSourceImpl @Inject constructor(
    private val perfumeService: RetrofitPerfumeService
): IWishListRemoteSource {
    // di를 쓰자

    override suspend fun getMyList(type: String?): Response<String> {
        return perfumeService.getMyList(type)

    }


}