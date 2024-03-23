package com.nane.detail.data.repo.impl

import com.nane.base.data.DataResult
import com.nane.detail.data.mapper.DetailDataMapper
import com.nane.detail.data.repo.IDetailRepository
import com.nane.detail.data.source.IDetailRemoteSource
import com.nane.detail.domain.data.PerfumeDetailDTO
import com.nane.network.parser.getParseErrorResult
import javax.inject.Inject

/**
 * Created by haul on 3/10/24
 */
class DetailRepositoryImpl @Inject constructor(
    private val remoteSource: IDetailRemoteSource,
    private val mapper: DetailDataMapper,
) : IDetailRepository {

    override suspend fun getPerfumeDetail(targetId: Int): DataResult<PerfumeDetailDTO> {
        val response = remoteSource.getPerfumeDetail(targetId)
        return if (response.isSuccessful) {
            DataResult.Success(mapper.toDto(response.body()?.perfume))
        } else {
            val failed = getParseErrorResult(response)
            DataResult.Failed(failed.errorMsg, failed.errorCode)
        }
    }

    override suspend fun patchPerfumeWish(perfumeId: Int): DataResult<Boolean> {
        val response = remoteSource.patchPerfumeWish(perfumeId)
        return if (response.isSuccessful) {
            DataResult.Success(response.body() ?: false)
        } else {
            val failed = getParseErrorResult(response)
            DataResult.Failed(failed.errorMsg, failed.errorCode)
        }
    }

    override suspend fun patchPerfumeHaving(perfumeId: Int): DataResult<Boolean> {
        val response = remoteSource.patchPerfumeHaving(perfumeId)
        return if (response.isSuccessful) {
            DataResult.Success(response.body() ?: false)
        } else {
            val failed = getParseErrorResult(response)
            DataResult.Failed(failed.errorMsg, failed.errorCode)
        }
    }
}