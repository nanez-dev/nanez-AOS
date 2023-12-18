package com.nane.theme.data.mapper

import com.nane.network.api.theme.BrandApi
import com.nane.theme.domain.data.BrandDTO
import com.nane.theme.domain.data.BrandDetailDomainDTO
import com.nane.theme.domain.data.PerfumeDomainDTO
import javax.inject.Inject

class BrandDetailDataMapper @Inject constructor() {
    fun toDTO(apiData: BrandApi.BrandDetail?): BrandDetailDomainDTO {
        return BrandDetailDomainDTO(
            brand = if (apiData?.brandItem != null) BrandDTO(
                id = apiData.brandItem.id,
                korName = apiData.brandItem.korName,
                engDescriptionBody = apiData.brandItem.engDescriptionBody,
                korDescriptionBody = apiData.brandItem.korDescriptionBody,
                relatedImgUrl = apiData.brandItem.relatedImgUrl,
                engName = apiData.brandItem.engName,
                engDescriptionTitle = apiData.brandItem.engDescriptionTitle,
                korDescriptionTitle = apiData.brandItem.korDescriptionTitle,
                imgUrl = apiData.brandItem.imgUrl
            ) else null,
            relatedPerfumes = apiData?.relatedPerfumes?.map {
                PerfumeDomainDTO(
                    id = it.id,
                    brand = BrandDTO(
                        id = it.brand.id,
                        korName = it.brand.korName,
                        engDescriptionBody = it.brand.engDescriptionBody,
                        korDescriptionBody = it.brand.korDescriptionBody,
                        relatedImgUrl = it.brand.relatedImgUrl,
                        engName = it.brand.engName,
                        engDescriptionTitle = it.brand.engDescriptionTitle,
                        korDescriptionTitle = it.brand.korDescriptionTitle,
                        imgUrl = it.brand.imgUrl
                    ),
                    korName = it.korName,
                    engName = it.engName,
                    imgUrl = it.imgUrl,
                    capacity = it.capacity,
                    price = it.price,
                    brandId = it.brandId,
                    isSingle = it.isSingle,
                    webImgUrl1 = it.webImgUrl1,
                    webImgUrl2 = it.webImgUrl2,
                    densityId = it.densityId,
                    title = it.title,
                    rating = it.rating,
                    description = it.description
                )
            } ?: emptyList()
        )
    }
}
