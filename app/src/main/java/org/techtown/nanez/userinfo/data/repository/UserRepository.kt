package org.techtown.nanez.userinfo.data.repository

import com.nane.base.data.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.techtown.nanez.data.api.users.SignInApi
import org.techtown.nanez.userinfo.data.data.UserLoginDTO
import org.techtown.nanez.userinfo.data.source.local.UserLocalDataSource
import org.techtown.nanez.userinfo.data.source.remote.UserRemoteDataSource
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
            emit(DataResult.Failed(""))
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
            emit(DataResult.Failed(response.message()))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    suspend fun saveLoginInfo(dto: UserLoginDTO) {
        localDataSource.saveUserLoginInfo(dto)
    }
}