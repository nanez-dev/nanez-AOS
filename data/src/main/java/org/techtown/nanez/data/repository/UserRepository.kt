package org.techtown.nanez.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.techtown.nanez.data.data.UserLoginDTO
import org.techtown.nanez.data.source.local.UserLocalDataSource
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource
){

    suspend fun getUserLoginInfo(): Flow<UserLoginDTO> = flow {
        emit(localDataSource.getUserLoginInfo())
    }
}