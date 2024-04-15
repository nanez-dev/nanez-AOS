package com.nane.network.service

import com.nane.network.api.users.JoinEmailAuthApi
import com.nane.network.api.users.JoinVerifyAuthEmailCodeApi
import com.nane.network.api.users.PasswordChangeApi
import com.nane.network.api.users.ProfileApi
import com.nane.network.api.users.ResetRandomPasswordApi
import com.nane.network.api.users.SignUpApi
import org.techtown.nanez.data.api.users.SignInApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by iseungjun on 2023/08/17
 */
interface RetrofitUserService {

    @GET("api/users/me")
    suspend fun getMyProfile(): Response<ProfileApi.Response>

    @POST("api/users/nickname-verify")
    suspend fun postNicknameVerify(@Query("nickname") nickname: String): Response<Boolean>

    @POST("api/users/signup")
    suspend fun postSignUp(@Body body: SignUpApi.Body): Response<SignUpApi.Response>

    @POST("api/users/email-send")
    suspend fun postSendAuthEmail(@Body body: JoinEmailAuthApi.Body): Response<Boolean>

    @POST("api/users/email-verify")
    suspend fun postEmailVerify(@Body body: JoinVerifyAuthEmailCodeApi.Body): Response<Boolean>

    @POST("api/users/signin")
    suspend fun postSignIn(@Body body: SignInApi.Body): Response<SignInApi.Response>

    @POST("api/users/reset-random-password")
    suspend fun postResetRandomPassword(@Body body: ResetRandomPasswordApi.Body): Response<Boolean>

    @DELETE("api/users/withdrawal")
    suspend fun deleteWithdraw(): Response<Boolean>

    @PATCH("api/users/password")
    suspend fun patchPassword(@Body body: PasswordChangeApi.Body): Response<Unit>
}