package com.nane.home.data.source.remote

import com.nane.home.data.source.IHomeRemoteSource
import com.nane.network.api.home.HomeApi
import com.nane.network.service.RetrofitPerfumeService
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/09/11
 */
class HomeRemoteSourceImpl @Inject constructor(
    private val perfumeService: RetrofitPerfumeService
) : IHomeRemoteSource {

    override suspend fun getHomeInfo(): Response<HomeApi.Response> {
        return perfumeService.getHomeInfo()
    }
}