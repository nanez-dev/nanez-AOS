package com.nane.join.domain.repo

import com.nane.base.data.DataResult
import com.nane.join.domain.data.JoinAccordDTO
import com.nane.join.domain.data.JoinSignUpDTO
import com.nane.network.api.users.SignUpApi
import kotlinx.coroutines.flow.Flow

/**
 * Created by haul on 10/30/23
 */
interface IJoinRepository {
    suspend fun postSignUp(signUpDTO: JoinSignUpDTO): Flow<DataResult<Boolean>>
    suspend fun postSendAuthEmail(email: String): Flow<DataResult<Boolean>>
    suspend fun postCheckAuthEmailCode(code: String, email: String): Flow<DataResult<Boolean>>
    suspend fun postCheckNickNameVerify(nickName: String): Flow<DataResult<Boolean>>
    suspend fun postCheckEventCodeVerify(code: String): Flow<DataResult<Boolean>>
    suspend fun getAllAccordList(): Flow<DataResult<List<JoinAccordDTO>>>
}