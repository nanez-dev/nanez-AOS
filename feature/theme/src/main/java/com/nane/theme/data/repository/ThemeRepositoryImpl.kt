package com.nane.theme.data.repository

import com.nane.base.data.DataResult
import com.nane.network.parser.getParseErrorResult
import com.nane.theme.data.mapper.AccordDataMapper
import com.nane.theme.data.mapper.BrandDataMapper
import com.nane.theme.data.source.IThemeRemoteSource
import com.nane.theme.domain.data.AccordDetailDomainDTO
import com.nane.theme.domain.data.AccordsDomainDTO
import com.nane.theme.domain.data.BrandDetailDomainDTO
import com.nane.theme.domain.data.BrandsDomainDTO
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
    private val remoteDataSource: IThemeRemoteSource,
    private val accordDataMapper: AccordDataMapper,
    private val brandDataMapper: BrandDataMapper,
) : IThemeRepository {

    override suspend fun getAccords(): Flow<DataResult<AccordsDomainDTO>> = flow {
        val response = remoteDataSource.getAccords()
        if (response.isSuccessful) {
            emit(DataResult.Success(accordDataMapper.toDTO(response.body())))
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    override suspend fun getBrands(): Flow<DataResult<BrandsDomainDTO>> = flow {
        val response = remoteDataSource.getBrands()
        if (response.isSuccessful) {
            emit(DataResult.Success(brandDataMapper.toDTO(response.body())))
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    override suspend fun getAccordDetail(id: Int): Flow<DataResult<AccordDetailDomainDTO>> = flow {
        val response = remoteDataSource.getAccordDetail(id = id)
        if (response.isSuccessful) {
            emit(DataResult.Success(accordDataMapper.toDetailDTO(response.body())))
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    override suspend fun getBrandDetail(brandId: Int): Flow<DataResult<BrandDetailDomainDTO>> = flow {
        val response = remoteDataSource.getBrandDetail(brandId = brandId)
        if (response.isSuccessful) {
            emit(DataResult.Success(brandDataMapper.toDetailDTO(response.body())))
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