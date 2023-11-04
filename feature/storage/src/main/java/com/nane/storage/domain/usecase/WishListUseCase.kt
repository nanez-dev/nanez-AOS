package com.nane.storage.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.storage.data.repo.IWishListRepository
import javax.inject.Inject

/**
 * Created by haul on 11/4/23
 */
class WishListUseCase @Inject constructor(
    private val repository: IWishListRepository
) {

    suspend fun getMyList(type: String?): DomainResult<String> {
        val dataResult = repository.getMyList(type)
        return when (dataResult) {
            is DataResult.Success -> {
                DomainResult.Success(dataResult.data)
            }
            is DataResult.Failed -> {
                DomainResult.Failed(dataResult.msg, dataResult.code)
            }
            is DataResult.Error -> {
                DomainResult.Error(dataResult.exception)
            }
        }
    }

}