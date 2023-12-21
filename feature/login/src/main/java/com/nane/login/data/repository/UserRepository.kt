package com.nane.login.data.repository

import com.nane.base.data.DataResult
import com.nane.login.data.data.UserLoginDTO
import com.nane.login.data.source.local.UserLocalDataSource
import com.nane.login.data.source.remote.UserRemoteDataSource
import com.nane.network.parser.getParseErrorResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.techtown.nanez.data.api.users.SignInApi
import org.techtown.nanez.utils.session.SessionManager
import java.io.IOException
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
){

    suspend fun getUserLoginInfo(): Flow<DataResult<UserLoginDTO>> = flow {
        val info = localDataSource.getUserLoginInfo()
        if (info.userEmail?.isNotEmpty() == true && info.userPassword?.isNotEmpty() == true){
            emit(DataResult.Success(info))
        } else {
            emit(DataResult.Failed("", 0))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    suspend fun postLogin(body: SignInApi.Body): Flow<DataResult<SignInApi.Response?>> = flow {
        val response = remoteDataSource.postLogin(body)
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


    suspend fun saveLoginInfo(dto: UserLoginDTO) {
        SessionManager.instance.saveEmail(dto.userEmail ?: "")
        SessionManager.instance.saveToken(dto.accessToken ?: "", dto.refreshToken ?: "")
        localDataSource.saveUserLoginInfo(dto)
    }
}