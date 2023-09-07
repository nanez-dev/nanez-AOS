package com.nane.home.data.repository

import com.nane.base.data.DataResult
import com.nane.home.data.source.remote.HomeRemoteTestDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import com.nane.network.api.home.HomeApi
import java.io.IOException
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeRepository @Inject constructor(
    private val remoteDataSource: HomeRemoteTestDataSource
) {

    suspend fun getHomeInfo(): Flow<DataResult<HomeApi.Response?>> = flow {
        val response = remoteDataSource.getHomeInfo()
        if (response.isSuccessful) {
            emit(DataResult.Success(response.body()))
        } else {
            emit(DataResult.Failed(response.message()))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)
}