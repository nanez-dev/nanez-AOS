package com.nane.profile.data.repo.impl

import com.nane.base.data.DataResult
import com.nane.network.parser.getParseErrorResult
import com.nane.profile.data.mapper.ProfileDataMapper
import com.nane.profile.data.repo.IProfileRepository
import com.nane.profile.data.source.IProfileRemoteSource
import com.nane.profile.domain.data.ProfileDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import org.techtown.nanez.utils.NaneLog
import java.io.IOException
import javax.inject.Inject

/**
 * Created by haul on 3/2/24
 */
class ProfileRepositoryImpl @Inject constructor(
    private val remoteSource: IProfileRemoteSource,
    private val mapper: ProfileDataMapper,
) : IProfileRepository {

    override suspend fun getMyProfile(): Flow<DataResult<ProfileDTO>> = flow {
        val response = remoteSource.getMyProfileInfo()
        if (response.isSuccessful) {
            emit(DataResult.Success(mapper.toProfileDto(response.body())))
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    override suspend fun getMyList(type: String?): DataResult<Int> {
        return try {
            val response = remoteSource.getMyList(type)
            if (response.isSuccessful) {
                DataResult.Success(response.body()?.size ?: 0)
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