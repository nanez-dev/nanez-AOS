package com.nane.password.data.source.impl

import com.nane.network.api.users.PasswordChangeApi
import com.nane.network.api.users.ResetRandomPasswordApi
import com.nane.network.service.RetrofitUserService
import com.nane.password.data.source.IPasswordRemoteSource
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by haul on 3/25/24
 */
class PasswordRemoteSourceImpl @Inject constructor(
    private val apiService: RetrofitUserService
) : IPasswordRemoteSource {

    override suspend fun patchPassword(body: PasswordChangeApi.Body): Response<Unit> {
        return apiService.patchPassword(body)
    }

    override suspend fun postRandomPassword(body: ResetRandomPasswordApi.Body): Response<Boolean> {
        return apiService.postResetRandomPassword(body)
    }
}