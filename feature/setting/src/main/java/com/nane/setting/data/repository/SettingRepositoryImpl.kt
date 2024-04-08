package com.nane.setting.data.repository

import com.nane.base.data.DataResult
import com.nane.network.parser.getParseErrorResult
import com.nane.setting.data.source.ISettingRemoteSource
import com.nane.setting.domain.repository.ISettingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import org.techtown.nanez.utils.session.SessionManager
import java.io.IOException
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val remoteDataSource: ISettingRemoteSource
): ISettingRepository {
    override suspend fun logOut() = flow {
        SessionManager.instance.logOut()
        emit(DataResult.Success(Unit))
    }.flowOn(Dispatchers.IO)

    override suspend fun withdraw(): Flow<DataResult<Unit>> = flow {
        val response = remoteDataSource.withdraw()
        if (response.isSuccessful) {
            emit(DataResult.Success(Unit))
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