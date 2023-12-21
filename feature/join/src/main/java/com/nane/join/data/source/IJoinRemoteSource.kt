package com.nane.join.data.source

import com.nane.network.api.theme.AccordApi
import com.nane.network.api.users.JoinEmailAuthApi
import com.nane.network.api.users.JoinVerifyAuthEmailCodeApi
import com.nane.network.api.users.SignUpApi
import retrofit2.Response

/**
 * Created by haul on 10/30/23
 */
interface IJoinRemoteSource {
    suspend fun postSignUp(body: SignUpApi.Body): Response<SignUpApi.Response>
    suspend fun postSendAuthEmail(body: JoinEmailAuthApi.Body): Response<Boolean>
    suspend fun postCheckAuthEmailCode(body: JoinVerifyAuthEmailCodeApi.Body): Response<Boolean>
    suspend fun postCheckNickNameVerify(nickName: String): Response<Boolean>
    suspend fun postCheckEventCodeVerify(code: String): Response<Boolean>
    suspend fun getAllAccordList(): Response<AccordApi.Accords>
}