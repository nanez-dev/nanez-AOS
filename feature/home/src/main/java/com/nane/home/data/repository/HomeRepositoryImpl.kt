package com.nane.home.data.repository

import com.nane.base.data.DataResult
import com.nane.home.data.mapper.HomeDataMapper
import com.nane.home.data.source.IHomeRemoteSource
import com.nane.home.domain.data.HomeInfoDomainDTO
import com.nane.home.domain.repository.IHomeRepository
import com.nane.network.parser.getParseErrorResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import java.io.IOException
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: IHomeRemoteSource,
    private val mapper: HomeDataMapper,
) : IHomeRepository {

    override suspend fun getHomeInfo(): Flow<DataResult<HomeInfoDomainDTO>> = flow {
        val response = remoteDataSource.getHomeInfo()
        if (response.isSuccessful) {
            emit(DataResult.Success(mapper.toDTO(response.body())))
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)
}