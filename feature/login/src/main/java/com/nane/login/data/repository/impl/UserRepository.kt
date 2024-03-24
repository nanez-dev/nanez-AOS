package com.nane.login.data.repository.impl

import com.nane.base.data.DataResult
import com.nane.login.data.mapper.UserDataMapper
import com.nane.login.data.repository.IUserRepository
import com.nane.login.data.source.IUserLocalDataSource
import com.nane.login.data.source.IUserRemoteDataSource
import com.nane.login.domain.data.UserLoginDomainDTO
import com.nane.network.parser.getParseErrorResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import org.techtown.nanez.data.api.users.SignInApi
import org.techtown.nanez.utils.session.SessionManager
import java.io.IOException
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserRepository @Inject constructor(
    private val localDataSource: IUserLocalDataSource,
    private val remoteDataSource: IUserRemoteDataSource,
    private val mapper: UserDataMapper,
) : IUserRepository {

    override suspend fun getUserLoginInfo(): Flow<DataResult<UserLoginDomainDTO>> = flow {
        val info = localDataSource.getUserLoginInfo()
        if (info.userEmail?.isNotEmpty() == true && info.userPassword?.isNotEmpty() == true){
            emit(DataResult.Success(mapper.toDomainDTO(info)))
        } else {
            emit(DataResult.Failed("", 0))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)


    override suspend fun postLogin(body: SignInApi.Body): Flow<DataResult<UserLoginDomainDTO>> = flow {
        val response = remoteDataSource.postLogin(body)
        if (response.isSuccessful) {
            val domainDto = mapper.toDomainDTO(body.email, body.password, response.body())
            saveLoginInfo(domainDto)
            emit(DataResult.Success(domainDto))
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)

    override suspend fun saveLoginInfo(dto: UserLoginDomainDTO) {
        SessionManager.instance.saveEmail(dto.email ?: "")
        SessionManager.instance.savePassword(dto.passWord ?: "")
        SessionManager.instance.saveToken(dto.accessToken ?: "", dto.refreshToken ?: "")
        localDataSource.saveUserLoginInfo(mapper.toData(dto))
    }
}