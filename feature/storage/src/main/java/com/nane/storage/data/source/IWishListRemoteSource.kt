package com.nane.storage.data.source

import retrofit2.Response

interface IWishListRemoteSource {
    suspend fun getMyList(type: String?): Response<String>
}