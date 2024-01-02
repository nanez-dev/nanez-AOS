package com.nane.join.data.repo.impl

import com.nane.base.data.DataResult
import com.nane.join.data.mapper.JoinDataMapper
import com.nane.join.data.source.IJoinLocalSource
import com.nane.join.data.source.IJoinRemoteSource
import com.nane.join.domain.data.JoinAccordDTO
import com.nane.join.domain.data.JoinSignUpDTO
import com.nane.join.domain.repo.IJoinRepository
import com.nane.network.api.users.JoinEmailAuthApi
import com.nane.network.api.users.JoinVerifyAuthEmailCodeApi
import com.nane.network.parser.getParseErrorResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.techtown.nanez.utils.NaneLog
import javax.inject.Inject

/**
 * Created by haul on 10/30/23
 */
class JoinRepositoryImpl @Inject constructor(
    private val remoteSource: IJoinRemoteSource,
    private val localSource: IJoinLocalSource,
    private val mapper: JoinDataMapper
) : IJoinRepository {

    override suspend fun postSignUp(signUpDTO: JoinSignUpDTO): Flow<DataResult<Boolean>> = flow {
        val response = remoteSource.postSignUp(mapper.toBody(signUpDTO))
        if (response.isSuccessful) {
            response.body()?.let {
                if (it.access_token?.isNotEmpty() == true && it.refresh_token?.isNotEmpty() == true) {
                    localSource.saveUserToken(it.access_token, it.refresh_token)
                    emit(DataResult.Success(true))
                } else {
                    emit(DataResult.Success(false))
                }
            } ?: run {
                emit(DataResult.Success(false))
            }
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.catch { t ->
        NaneLog.e(t)
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    override suspend fun postSendAuthEmail(email: String): Flow<DataResult<Boolean>> = flow {
        val response = remoteSource.postSendAuthEmail(JoinEmailAuthApi.Body(email))
        if (response.isSuccessful) {
            response.body()?.let {
                emit(DataResult.Success(it))
            } ?: run {
                emit(DataResult.Success(false))
            }
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.catch { t ->
        NaneLog.e(t)
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    override suspend fun postCheckAuthEmailCode(code: String, email: String): Flow<DataResult<Boolean>> = flow {
        val response = remoteSource.postCheckAuthEmailCode(JoinVerifyAuthEmailCodeApi.Body(code, email))
        if (response.isSuccessful) {
            response.body()?.let {
                emit(DataResult.Success(it))
            } ?: run {
                emit(DataResult.Success(false))
            }
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.catch { t ->
        NaneLog.e(t)
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    override suspend fun postCheckNickNameVerify(nickName: String): Flow<DataResult<Boolean>> = flow {
        val response = remoteSource.postCheckNickNameVerify(nickName)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(DataResult.Success(it))
            } ?: run {
                emit(DataResult.Success(false))
            }
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.catch { t ->
        NaneLog.e(t)
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    override suspend fun postCheckEventCodeVerify(code: String): Flow<DataResult<Boolean>> = flow {
        val response = remoteSource.postCheckEventCodeVerify(code)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(DataResult.Success(it))
            } ?: run {
                emit(DataResult.Success(false))
            }
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.catch { t ->
        NaneLog.e(t)
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    override suspend fun getAllAccordList(): Flow<DataResult<List<JoinAccordDTO>>> = flow {
        val response = remoteSource.getAllAccordList()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                emit(DataResult.Success(result.accords?.map { mapper.toAccordDTO(it) } ?: emptyList<JoinAccordDTO>()))
            } ?: run {
                emit(DataResult.Success(emptyList<JoinAccordDTO>()))
            }
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.catch { t ->
        NaneLog.e(t)
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)
}