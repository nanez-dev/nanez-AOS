package com.nane.login.data.source

import org.techtown.nanez.data.api.users.SignInApi
import retrofit2.Response

/**
 * Created by haul on 3/2/24
 */
interface IUserRemoteDataSource {
    suspend fun postLogin(body: SignInApi.Body): Response<SignInApi.Response>
}