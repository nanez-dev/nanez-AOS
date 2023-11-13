package com.nane.storage.data.source

import com.nane.network.api.storage.StorageApi
import retrofit2.Response

interface IWishListRemoteSource {
    suspend fun getMyList(type: String?): Response<StorageApi.Response>
}