package com.nane.theme.data.repository

import com.nane.base.data.DataResult
import com.nane.network.api.theme.AccordApi
import com.nane.network.api.theme.BrandApi
import com.nane.network.parser.getParseErrorResult
import com.nane.theme.data.source.IThemeRemoteSource
import com.nane.theme.domain.repository.IThemeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import java.io.IOException
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val remoteDataSource: IThemeRemoteSource
) : IThemeRepository {

    override suspend fun getPopularAccords(): Flow<DataResult<AccordApi.Accords?>>
        = flow {
            val response = remoteDataSource.getPopularAccords()
            if (response.isSuccessful) {
                emit(DataResult.Success(response.body()))
            } else {
                val failed = getParseErrorResult(response)
                emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
            }
        }.retry(2) { cause ->
            cause is IOException
        }.catch { t ->
            emit(DataResult.Error(Exception(t)))
        }.flowOn(Dispatchers.IO)

    override suspend fun getAllAccords(): Flow<DataResult<AccordApi.Accords?>>
        = flow {
            val response = remoteDataSource.getAllAccords()
            if (response.isSuccessful) {
                emit(DataResult.Success(response.body()))
            } else {
                val failed = getParseErrorResult(response)
                emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
            }
        }.retry(2) { cause ->
            cause is IOException
        }.catch { t ->
            emit(DataResult.Error(Exception(t)))
        }.flowOn(Dispatchers.IO)

    override suspend fun getAllBrands(): Flow<DataResult<BrandApi.Brands?>>
        = flow {
            val response = remoteDataSource.getAllBrands()
            if (response.isSuccessful) {
                emit(DataResult.Success(response.body()))
            } else {
                val failed = getParseErrorResult(response)
                emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
            }
        }.retry(2) { cause ->
            cause is IOException
        }.catch { t ->
            emit(DataResult.Error(Exception(t)))
        }.flowOn(Dispatchers.IO)

    override suspend fun getPopularBrands(): Flow<DataResult<BrandApi.Brands?>>
        = flow {
            val response = remoteDataSource.getPopularBrands()
            if (response.isSuccessful) {
                emit(DataResult.Success(response.body()))
            } else {
                val failed = getParseErrorResult(response)
                emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
            }
        }.retry(2) { cause ->
            cause is IOException
        }.catch { t ->
            emit(DataResult.Error(Exception(t)))
        }.flowOn(Dispatchers.IO)

    override suspend fun getAccordDetail(id: Int): Flow<DataResult<AccordApi.AccordDetail?>>
        = flow {
            val response = remoteDataSource.getAccordDetail(id = id)
            if (response.isSuccessful) {
                emit(DataResult.Success(response.body()))
            } else {
                val failed = getParseErrorResult(response)
                emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
            }
        }.retry(2) { cause ->
            cause is IOException
        }.catch { t ->
            emit(DataResult.Error(Exception(t)))
        }.flowOn(Dispatchers.IO)

    override suspend fun getBrandDetail(
        brandId: Int,
        limit: Int
    ): Flow<DataResult<BrandApi.BrandDetail?>>
        = flow {
            val response = remoteDataSource.getBrandDetail(brandId = brandId, limit = limit)
            if (response.isSuccessful) {
                emit(DataResult.Success(response.body()))
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