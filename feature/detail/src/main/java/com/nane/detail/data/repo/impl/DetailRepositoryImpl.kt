package com.nane.detail.data.repo.impl

import com.nane.base.data.DataResult
import com.nane.detail.data.mapper.DetailDataMapper
import com.nane.detail.data.repo.IDetailRepository
import com.nane.detail.data.source.IDetailRemoteSource
import com.nane.detail.domain.data.PerfumeDetailDTO
import com.nane.network.parser.getParseErrorResult
import org.techtown.nanez.utils.NaneLog
import javax.inject.Inject

/**
 * Created by haul on 3/10/24
 */
class DetailRepositoryImpl @Inject constructor(
    private val remoteSource: IDetailRemoteSource,
    private val mapper: DetailDataMapper,
) : IDetailRepository {

    override suspend fun getPerfumeDetail(targetId: Int): DataResult<PerfumeDetailDTO> {
        return try{
            val response = remoteSource.getPerfumeDetail(targetId)
            if (response.isSuccessful) {
                DataResult.Success(mapper.toDto(response.body()?.perfume))
            } else {
                val failed = getParseErrorResult(response)
                DataResult.Failed(failed.errorMsg, failed.errorCode)
            }
        } catch (e: Exception) {
            NaneLog.e(e)
            DataResult.Error(e)
        }
    }

    override suspend fun patchPerfumeWish(perfumeId: Int): DataResult<Boolean> {
        return try {
            val response = remoteSource.patchPerfumeWish(perfumeId)
            if (response.isSuccessful) {
                DataResult.Success(response.body() ?: false)
            } else {
                val failed = getParseErrorResult(response)
                DataResult.Failed(failed.errorMsg, failed.errorCode)
            }
        } catch (e: Exception) {
            NaneLog.e(e)
            DataResult.Error(e)
        }
    }

    override suspend fun patchPerfumeHaving(perfumeId: Int): DataResult<Boolean> {
        return try {
            val response = remoteSource.patchPerfumeHaving(perfumeId)
            if (response.isSuccessful) {
                DataResult.Success(response.body() ?: false)
            } else {
                val failed = getParseErrorResult(response)
                DataResult.Failed(failed.errorMsg, failed.errorCode)
            }
        } catch (e: Exception) {
            NaneLog.e(e)
            DataResult.Error(e)
        }
    }
}