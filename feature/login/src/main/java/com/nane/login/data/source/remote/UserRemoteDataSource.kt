package com.nane.login.data.source.remote

import com.nane.login.data.source.IUserRemoteDataSource
import com.nane.network.service.RetrofitUserService
import org.techtown.nanez.data.api.users.SignInApi
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/09/04
 */
class UserRemoteDataSource @Inject constructor(
    private val userService: RetrofitUserService
) : IUserRemoteDataSource {

    override suspend fun postLogin(body: SignInApi.Body): Response<SignInApi.Response> {
        return userService.postSignIn(body)
    }
}