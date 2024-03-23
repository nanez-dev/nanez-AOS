package com.nane.storage.domain.mapper

import com.nane.storage.domain.data.StorageDomainDTO
import com.nane.storage.presentation.data.StorageViewData
import javax.inject.Inject

/**
 * Created by haul on 3/2/24
 */
class StorageDomainMapper @Inject constructor() {

    fun toViewData(dto: StorageDomainDTO): StorageViewData.StorageItem {
        return StorageViewData.StorageItem.fromApiModel(dto)
    }
}