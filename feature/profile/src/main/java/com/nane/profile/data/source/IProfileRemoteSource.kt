package com.nane.profile.data.source

import com.nane.base.data.DataResult
import com.nane.network.api.storage.StorageApi
import com.nane.network.api.users.ProfileApi
import com.nane.network.api.users.PasswordChangeApi
import retrofit2.Response

/**
 * Created by haul on 3/2/24
 */
interface IProfileRemoteSource {
    suspend fun getMyProfileInfo(): Response<ProfileApi.Response>
    suspend fun getMyList(type: String?): Response<List<StorageApi.Response>>
}