package com.nane.join.data.source.impl

import com.nane.join.data.source.IJoinRemoteSource
import com.nane.network.api.theme.AccordApi
import com.nane.network.api.users.JoinEmailAuthApi
import com.nane.network.api.users.JoinVerifyAuthEmailCodeApi
import com.nane.network.api.users.SignUpApi
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

    override suspend fun postSignUp(body: SignUpApi.Body): Response<SignUpApi.Response> {
        return userService.postSignUp(body)
    }

    override suspend fun postSendAuthEmail(body: JoinEmailAuthApi.Body): Response<Boolean> {
        return userService.postSendAuthEmail(body)
    }

    override suspend fun postCheckAuthEmailCode(body: JoinVerifyAuthEmailCodeApi.Body): Response<Boolean> {
        return userService.postEmailVerify(body)
    }

    override suspend fun postCheckNickNameVerify(nickName: String): Response<Boolean> {
        return userService.postNicknameVerify(nickName)
    }

    // TODO 이벤트 코드 체크
    override suspend fun postCheckEventCodeVerify(code: String): Response<Boolean> {
        return userService.postNicknameVerify(code)
    }

    override suspend fun getAllAccordList(): Response<AccordApi.Accords> {
        return accordService.getAccords()
    }
}