package com.nane.storage.data.repository

import com.nane.base.data.DataResult
import com.nane.storage.domain.data.StorageDomainDTO

interface IStorageRepository {
    suspend fun getMyList(type: String?): DataResult<List<StorageDomainDTO>>
}