package com.nane.theme.presentation.mapper

import com.nane.theme.domain.data.AccordDetailDomainDTO
import com.nane.theme.presentation.data.AccordDetailViewData
import com.nane.theme.presentation.data.AccordItemViewData
import com.nane.theme.presentation.data.BrandItemViewData
import com.nane.theme.presentation.data.PerfumeViewData
import javax.inject.Inject

class AccordDetailDomainMapper @Inject constructor() {

    fun toViewData(dto: AccordDetailDomainDTO): AccordDetailViewData {
        return AccordDetailViewData(
            accordItemViewData = dto.accord?.let { accord ->
                AccordItemViewData(
                    engName = accord.engName,
                    korName = accord.korName,
                    imgUrl = accord.imgUrl,
                    id = accord.id,
                    engDescriptionTitle = accord.engDescriptionTitle,
                    korDescriptionTitle = accord.korDescriptionTitle,
                    engDescriptionBody = accord.engDescriptionBody,
                    korDescriptionBody = accord.korDescriptionBody,
                    code = accord.code,
                    relatedImgUrl = accord.relatedImgUrl
                )
            },
            relatedPerfumes = dto.relatedPerfumes?.map {
                PerfumeViewData(
                    korName = it.korName,
                    engName = it.engName,
                    id = it.id,
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
                    imgUrl = it.imgUrl,
                    webImgUrl1 = it.webImgUrl1,
                    webImgUrl2 = it.webImgUrl2,
                    capacity = it.capacity,
                    densityId = it.densityId,
                    price = it.price,
                    title = it.title,
                    description = it.description,
                    rating = it.rating
                )
            } ?: emptyList()
        )
    }
}