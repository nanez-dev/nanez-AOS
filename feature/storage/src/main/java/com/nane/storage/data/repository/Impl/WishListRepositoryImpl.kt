package com.nane.storage.data.repository.Impl

import com.nane.base.data.DataResult
import com.nane.network.parser.getParseErrorResult
import com.nane.storage.data.repository.IWishListRepository
import com.nane.storage.data.source.IWishListRemoteSource
import javax.inject.Inject

class WishListRepositoryImpl @Inject constructor(
    private val remoteSource: IWishListRemoteSource
) : IWishListRepository {
    override suspend fun getMyList(type: String?): DataResult<String> {
        val response = remoteSource.getMyList(type)
        return if (response.isSuccessful) {
            DataResult.Success(response.body()?: "")
        } else {
            val failed = getParseErrorResult(response)
            DataResult.Failed(failed.errorMsg, failed.errorCode)
        }
    }
}