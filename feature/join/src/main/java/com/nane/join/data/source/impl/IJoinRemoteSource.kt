package com.nane.join.data.source.impl

import com.nane.network.api.users.JoinEmailAuthApi
import com.nane.network.api.users.JoinVerifyAuthEmailCodeApi
import retrofit2.Response

/**
 * Created by haul on 10/30/23
 */
interface IJoinRemoteSource {
    suspend fun postSendAuthEmail(body: JoinEmailAuthApi.Body): Response<Boolean>
    suspend fun postCheckAuthEmailCode(body: JoinVerifyAuthEmailCodeApi.Body): Response<Boolean>
    suspend fun postCheckNickNameVerify(nickName: String): Response<Boolean>
    suspend fun getAccordList(): Response<>
}