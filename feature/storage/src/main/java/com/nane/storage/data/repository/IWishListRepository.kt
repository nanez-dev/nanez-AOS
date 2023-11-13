package com.nane.storage.data.repository

import com.nane.base.data.DataResult
import com.nane.network.api.storage.StorageApi
import retrofit2.Response

interface IWishListRepository {
    suspend fun getMyList(type: String?): DataResult<StorageApi.Response?>
}