package com.nane.profile.data.source.impl

import com.nane.network.api.users.ProfileApi
import com.nane.network.service.RetrofitUserService
import com.nane.profile.data.source.IProfileRemoteSource
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by haul on 3/2/24
 */
class ProfileRemoteSourceImpl @Inject constructor(
    private val apiService: RetrofitUserService,
) : IProfileRemoteSource {

    override suspend fun getMyProfileInfo(): Response<ProfileApi.Response> {
        return apiService.getMyProfile()
    }
}