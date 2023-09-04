package org.techtown.nanez.data.source.remote

import org.techtown.nanez.data.api.users.SignInApi
import org.techtown.nanez.data.network.service.RetrofitUserService
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/09/04
 */
class UserRemoteDataSource @Inject constructor(
    private val userService: RetrofitUserService
) {

    fun postLogin(body: SignInApi.Body): Response<SignInApi.Response> {
        return userService.postSignIn(body)
    }
}