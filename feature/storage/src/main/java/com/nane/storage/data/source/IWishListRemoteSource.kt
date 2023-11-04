package com.nane.storage.data.source

import retrofit2.Response

/**
 * Created by haul on 11/4/23
 */
interface IWishListRemoteSource {
    suspend fun getMyList(type: String?): Response<String>
}