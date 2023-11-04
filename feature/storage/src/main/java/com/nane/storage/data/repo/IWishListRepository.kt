package com.nane.storage.data.repo

import com.nane.base.data.DataResult

/**
 * Created by haul on 11/4/23
 */
interface IWishListRepository {
    suspend fun getMyList(type: String?): DataResult<String>
}