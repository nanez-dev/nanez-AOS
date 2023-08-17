package org.techtown.nanez.data.network.service

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
    fun postValidationEmailCodeSend(@Body body: ValidationEmailApi.Body): Response<String>

    @POST("api/users/email-verify")
    fun postEmailVerify(@Body body: EmailVerifyApi.Body): Response<Boolean>

    @POST("api/users/nickname-verify?nickname={nickname}")
    fun postNicknameVerify(@Query("nickname") nickname: String): Response<Boolean>

    @POST("api/users/signup")
    fun postSignUp(@Body body: SignUpApi.Body): Response<SignUpApi.Response>

    @POST("api/users/signin")
    fun postSignIn(@Body body: SignInApi.Body): Response<SignInApi.Response>

    @DELETE("api/users/withdrawal")
    fun deleteWithdraw(): Response<String>

}