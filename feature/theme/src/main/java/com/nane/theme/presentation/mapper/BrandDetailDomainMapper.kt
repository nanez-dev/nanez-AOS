package com.nane.theme.presentation.mapper

import com.nane.theme.domain.data.BrandDetailDomainDTO
import com.nane.theme.presentation.data.BrandDetailViewData
import com.nane.theme.presentation.data.BrandItemViewData
import com.nane.theme.presentation.data.PerfumeViewData
import javax.inject.Inject

class BrandDetailDomainMapper @Inject constructor() {

    fun toViewData(dto: BrandDetailDomainDTO): BrandDetailViewData {
        return BrandDetailViewData(
            brandItemViewData = dto.brand?.let { brand ->
                BrandItemViewData(
                    id = brand.id,
                    korName = brand.korName,
                    engDescriptionBody = brand.engDescriptionBody,
                    korDescriptionBody = brand.korDescriptionBody,
                    relatedImgUrl = brand.relatedImgUrl,
                    engName = brand.engName,
                    engDescriptionTitle = brand.engDescriptionTitle,
                    korDescriptionTitle = brand.korDescriptionTitle,
                    imgUrl = brand.imgUrl
                )
            },
            relatedPerfumes = dto.relatedPerfumes?.map {
                PerfumeViewData(
                    id = it.id,
                    korName = it.korName,
                    engName = it.engName,
                    imgUrl = it.imgUrl,
                    capacity = it.capacity,
                    price = it.price,
                    brand = it.brand?.let { brand ->
                        BrandItemViewData(
                            id = brand.id,
                            korName = brand.korName,
                            engDescriptionBody = brand.engDescriptionBody,
                            korDescriptionBody = brand.korDescriptionBody,
                            relatedImgUrl = brand.relatedImgUrl,
                            engName = brand.engName,
                            engDescriptionTitle = brand.engDescriptionTitle,
                            korDescriptionTitle = brand.korDescriptionTitle,
                            imgUrl = brand.imgUrl
                        )
                    },
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