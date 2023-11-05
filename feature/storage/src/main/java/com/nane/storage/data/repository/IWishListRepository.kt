package com.nane.storage.data.repository

import com.nane.base.data.DataResult

interface IWishListRepository {
    suspend fun getMyList(type: String?): DataResult<String>
}