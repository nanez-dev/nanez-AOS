package com.nane.home.domain.repository

import com.nane.base.data.DataResult
import com.nane.home.domain.data.HomeInfoDomainDTO
import kotlinx.coroutines.flow.Flow

/**
 * Created by iseungjun on 2023/09/11
 */
interface IHomeRepository {
    suspend fun getHomeInfo(): Flow<DataResult<HomeInfoDomainDTO>>
}