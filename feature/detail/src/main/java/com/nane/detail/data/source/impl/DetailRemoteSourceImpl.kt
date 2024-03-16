package com.nane.detail.data.source.impl

import com.nane.detail.data.source.IDetailRemoteSource
import com.nane.network.api.detail.PerfumeDetailApi
import com.nane.network.service.RetrofitPerfumeService
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by haul on 3/10/24
 */
class DetailRemoteSourceImpl @Inject constructor(
    private val api: RetrofitPerfumeService
) : IDetailRemoteSource {

    override suspend fun getPerfumeDetail(targetId: Int): Response<PerfumeDetailApi.Response> {
        return api.getPerfumeDetail(targetId)
    }

    override suspend fun patchPerfumeWish(perfumeId: Int): Response<Boolean> {
        return api.patchPerfumeWish(perfumeId)
    }

    override suspend fun patchPerfumeHaving(perfumeId: Int): Response<Boolean> {
        return api.patchPerfumeHaving(perfumeId)
    }
}