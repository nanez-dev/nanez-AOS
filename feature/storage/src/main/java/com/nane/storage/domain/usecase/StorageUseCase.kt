package com.nane.storage.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.storage.data.repository.IStorageRepository
import com.nane.storage.domain.mapper.StorageDomainMapper
import com.nane.storage.presentation.data.StorageViewData
import javax.inject.Inject

class StorageUseCase @Inject constructor(
    private val repository: IStorageRepository,
    private val mapper: StorageDomainMapper,
) {
    suspend fun getMyList(type: String?): DomainResult<List<StorageViewData.StorageItem>> {
        return when (val dataResult = repository.getMyList(type)) {
            is DataResult.Success -> {
                DomainResult.Success(dataResult.data.map { mapper.toViewData(it) })
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