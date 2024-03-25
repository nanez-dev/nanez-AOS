package com.nane.password.data.source

import com.nane.network.api.users.PasswordChangeApi
import com.nane.network.api.users.ResetRandomPasswordApi
import retrofit2.Response

/**
 * Created by haul on 3/25/24
 */
interface IPasswordRemoteSource {
    suspend fun patchPassword(body: PasswordChangeApi.Body): Response<Unit>
    suspend fun postRandomPassword(body: ResetRandomPasswordApi.Body): Response<Boolean>
}