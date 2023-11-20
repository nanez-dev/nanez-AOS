package com.nane.join.data.source.impl

import com.nane.network.api.theme.AccordApi
import com.nane.network.api.users.JoinEmailAuthApi
import com.nane.network.api.users.JoinVerifyAuthEmailCodeApi
import com.nane.network.service.RetrofitAccordService
import com.nane.network.service.RetrofitUserService
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by haul on 10/30/23
 */
class JoinRemoteSourceImpl @Inject constructor(
    private val userService: RetrofitUserService,
    private val accordService: RetrofitAccordService
): IJoinRemoteSource {

    override suspend fun postSendAuthEmail(body: JoinEmailAuthApi.Body): Response<Boolean> {
        return userService.postSendAuthEmail(body)
    }

    override suspend fun postCheckAuthEmailCode(body: JoinVerifyAuthEmailCodeApi.Body): Response<Boolean> {
        return userService.postEmailVerify(body)
    }

    override suspend fun postCheckNickNameVerify(nickName: String): Response<Boolean> {
        return userService.postNicknameVerify(nickName)
    }

    override suspend fun getAllAccordList(): Response<AccordApi.Accords> {
        return accordService.getAllAccords()
    }
}