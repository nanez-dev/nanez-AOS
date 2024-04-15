package com.nane.password.data.repo.impl

import com.nane.base.data.DataResult
import com.nane.network.api.users.PasswordChangeApi
import com.nane.network.api.users.ResetRandomPasswordApi
import com.nane.network.parser.getParseErrorResult
import com.nane.password.data.repo.IPasswordRepository
import com.nane.password.data.source.IPasswordRemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.techtown.nanez.utils.NaneLog
import org.techtown.nanez.utils.session.SessionManager
import javax.inject.Inject

/**
 * Created by haul on 3/25/24
 */
class PasswordRepositoryImpl @Inject constructor(
    private val remoteSource: IPasswordRemoteSource,
) : IPasswordRepository {

    override suspend fun patchChangeMyPassword(currentPassword: String, newPassword: String): Flow<DataResult<Unit>> = flow {
        val response = remoteSource.patchPassword(PasswordChangeApi.Body(currentPassword, newPassword))
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

    override suspend fun logOut(): Flow<DataResult<Unit>> = flow {
        SessionManager.instance.logOut()
        emit(DataResult.Success(Unit))
    }.flowOn(Dispatchers.IO)


    override suspend fun postRandomPassword(email: String): DataResult<Boolean> {
        return try {
            val response = remoteSource.postRandomPassword(ResetRandomPasswordApi.Body(email))
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