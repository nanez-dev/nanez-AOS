package com.nane.profile.data.source.impl

import com.nane.network.api.storage.StorageApi
import com.nane.network.api.users.ProfileApi
import com.nane.network.api.users.PasswordChangeApi
import com.nane.network.service.RetrofitPerfumeService
import com.nane.network.service.RetrofitUserService
import com.nane.profile.data.source.IProfileRemoteSource
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by haul on 3/2/24
 */
class ProfileRemoteSourceImpl @Inject constructor(
    private val apiService: RetrofitUserService,
    private val perfumeService: RetrofitPerfumeService,
) : IProfileRemoteSource {

    override suspend fun getMyProfileInfo(): Response<ProfileApi.Response> {
        return apiService.getMyProfile()
    }

    override suspend fun getMyList(type: String?): Response<List<StorageApi.Response>> {
        return perfumeService.getMyList(type)
    }
}