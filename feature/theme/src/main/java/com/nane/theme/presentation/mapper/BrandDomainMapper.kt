package com.nane.theme.presentation.mapper

import com.nane.theme.domain.data.BrandsDomainDTO
import com.nane.theme.presentation.data.BrandItemViewData
import com.nane.theme.presentation.data.BrandViewData
import javax.inject.Inject

class BrandDomainMapper @Inject constructor() {

    fun toViewData(dto: BrandsDomainDTO): BrandViewData {
        return BrandViewData(
            popularBrands = dto.popularBrands.map {
                BrandItemViewData(
                    id = it.id,
                    korName = it.korName,
                    korDescriptionTitle = it.korDescriptionTitle,
                    korDescriptionBody = it.korDescriptionBody,
                    imgUrl = it.imgUrl,
                    relatedImgUrl = it.relatedImgUrl,
                    engName = it.engName
                )
            },
            allBrands = dto.allBrands.map {
                BrandItemViewData(
                    id = it.id,
                    korName = it.korName,
                    korDescriptionTitle = it.korDescriptionTitle,
                    korDescriptionBody = it.korDescriptionBody,
                    imgUrl = it.imgUrl,
                    relatedImgUrl = it.relatedImgUrl,
                    engName = it.engName
                )
            }
        )
    }
}