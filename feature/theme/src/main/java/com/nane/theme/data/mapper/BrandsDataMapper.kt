package com.nane.theme.data.mapper

import com.nane.network.api.theme.BrandApi
import com.nane.theme.domain.data.BrandDTO
import com.nane.theme.domain.data.BrandsDomainDTO
import javax.inject.Inject

class BrandsDataMapper @Inject constructor() {
    fun toDTO(apiData: BrandApi.Brands?): BrandsDomainDTO {
        return BrandsDomainDTO(
            itemList = apiData?.brands?.map {
                BrandDTO(
                    engTitle = it.engTitle,
                    korTitle = it.korTitle,
                    imageUrl = it.imageUrl,
                    id = it.id
                ) } ?: emptyList()
        )
    }
}
