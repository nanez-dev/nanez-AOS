package com.nane.theme.data.mapper

import com.nane.network.api.theme.BrandApi
import com.nane.theme.domain.data.BrandDTO
import com.nane.theme.domain.data.BrandsDomainDTO
import javax.inject.Inject

class BrandsDataMapper @Inject constructor() {
    fun toDTO(apiData: BrandApi.Brands?): BrandsDomainDTO {
        return BrandsDomainDTO(
            popularBrands = apiData?.popularBrands?.map {
                BrandDTO(
                    engName = it.engName,
                    korName = it.korName,
                    id = it.id,
                    engDescriptionBody = it.engDescriptionBody,
                    korDescriptionBody = it.korDescriptionBody,
                    relatedImgUrl = it.relatedImgUrl,
                    engDescriptionTitle = it.engDescriptionTitle,
                    korDescriptionTitle = it.korDescriptionTitle,
                    imgUrl = it.imgUrl
                )
            } ?: emptyList(),
            allBrands = apiData?.brands?.map {
                BrandDTO(
                    engName = it.engName,
                    korName = it.korName,
                    id = it.id,
                    engDescriptionBody = it.engDescriptionBody,
                    korDescriptionBody = it.korDescriptionBody,
                    relatedImgUrl = it.relatedImgUrl,
                    engDescriptionTitle = it.engDescriptionTitle,
                    korDescriptionTitle = it.korDescriptionTitle,
                    imgUrl = it.imgUrl
                ) } ?: emptyList()
        )
    }
}
