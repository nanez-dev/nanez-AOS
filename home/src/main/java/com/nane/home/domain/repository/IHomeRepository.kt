package com.nane.home.domain.repository

import com.nane.base.data.DataResult
import com.nane.network.api.home.HomeApi
import kotlinx.coroutines.flow.Flow

/**
 * Created by iseungjun on 2023/09/11
 */
interface IHomeRepository {
    suspend fun getHomeInfo(): Flow<DataResult<HomeApi.Response?>>
}