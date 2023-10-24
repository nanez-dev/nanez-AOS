package com.nane.network.service

import org.techtown.nanez.data.api.users.EmailVerifyApi
import org.techtown.nanez.data.api.users.SignInApi
import org.techtown.nanez.data.api.users.SignUpApi
import org.techtown.nanez.data.api.users.ValidationEmailApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by iseungjun on 2023/08/17
 */
interface RetrofitUserService {

    @POST("api/users/email-send")
    suspend fun postValidationEmailCodeSend(@Body body: ValidationEmailApi.Body): Response<String>

    @POST("api/users/email-verify")
    suspend fun postEmailVerify(@Body body: EmailVerifyApi.Body): Response<Boolean>

    @POST("api/users/nickname-verify?nickname={nickname}")
    suspend fun postNicknameVerify(@Query("nickname") nickname: String): Response<Boolean>

    @POST("api/users/signup")
    suspend fun postSignUp(@Body body: SignUpApi.Body): Response<SignUpApi.Response>

    @POST("api/users/signin")
    suspend fun postSignIn(@Body body: SignInApi.Body): Response<SignInApi.Response>

    @DELETE("api/users/withdrawal")
    suspend fun deleteWithdraw(): Response<String>

}