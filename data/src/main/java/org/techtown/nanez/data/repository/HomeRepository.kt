package org.techtown.nanez.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.techtown.nanez.data.api.home.HomeApi
import org.techtown.nanez.data.data.DataResult
import org.techtown.nanez.data.source.remote.HomeRemoteTestDataSource
import retrofit2.Response
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