package com.nane.detail.data.source

import com.nane.network.api.detail.PerfumeDetailApi
import retrofit2.Response

/**
 * Created by haul on 3/10/24
 */
interface IDetailRemoteSource {
    suspend fun getPerfumeDetail(targetId: Int): Response<PerfumeDetailApi.Response>
}