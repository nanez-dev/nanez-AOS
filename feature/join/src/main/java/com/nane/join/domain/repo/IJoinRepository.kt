package com.nane.join.domain.repo

import com.nane.base.data.DataResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by haul on 10/30/23
 */
interface IJoinRepository {
    suspend fun postSendAuthEmail(email: String): Flow<DataResult<Boolean>>
    suspend fun postCheckAuthEmailCode(code: String, email: String): Flow<DataResult<Boolean>>
    suspend fun postCheckNickNameVerify(nickName: String): Flow<DataResult<Boolean>>
}