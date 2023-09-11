package com.nane.home.data.source

import com.nane.network.api.home.HomeApi
import retrofit2.Response

/**
 * Created by iseungjun on 2023/09/11
 */
interface IHomeRemoteSource {
   suspend fun getHomeInfo(): Response<HomeApi.Response>
}