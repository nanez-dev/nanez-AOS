package com.nane.profile.data.repo.impl

import com.nane.base.data.DataResult
import com.nane.network.api.users.PasswordChangeApi
import com.nane.network.parser.getParseErrorResult
import com.nane.profile.data.mapper.ProfileDataMapper
import com.nane.profile.data.repo.IProfileRepository
import com.nane.profile.data.source.IProfileLocalSource
import com.nane.profile.data.source.IProfileRemoteSource
import com.nane.profile.domain.data.ProfileDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import org.techtown.nanez.utils.session.SessionManager
import java.io.IOException
import javax.inject.Inject

/**
 * Created by haul on 3/2/24
 */
class ProfileRepositoryImpl @Inject constructor(
    private val remoteSource: IProfileRemoteSource,
    private val localSource: IProfileLocalSource,
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

    override suspend fun patchChangeMyPassword(currentPassword: String, newPassword: String): Flow<DataResult<Unit>> = flow {
        val response = remoteSource.changeMyPassword(PasswordChangeApi.Body(currentPassword, newPassword))
        if (response.isSuccessful) {
            logOut().collect {
                emit(DataResult.Success(Unit))
            }
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)

    override suspend fun logOut(): Flow<DataResult<Unit>>  = flow {
        localSource.clearUserLoginInfo()
        SessionManager.instance.logOut()
        emit(DataResult.Success(Unit))
    }.flowOn(Dispatchers.IO)
}