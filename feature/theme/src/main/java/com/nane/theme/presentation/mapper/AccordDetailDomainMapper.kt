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
            accordItemViewData = if (dto.accord != null) AccordItemViewData(
                engName = dto.accord.engName,
                korName = dto.accord.korName,
                imgUrl = dto.accord.imgUrl,
                id = dto.accord.id,
                engDescriptionTitle = dto.accord.engDescriptionTitle,
                korDescriptionTitle = dto.accord.korDescriptionTitle,
                engDescriptionBody = dto.accord.engDescriptionBody,
                korDescriptionBody = dto.accord.korDescriptionBody,
                code = dto.accord.code,
                relatedImgUrl = dto.accord.relatedImgUrl
            ) else null,
            relatedPerfumes = dto.relatedPerfumes?.map {
                PerfumeViewData(
                    korName = it.korName,
                    engName = it.engName,
                    id = it.id,
                    brand = if (it.brand != null) BrandItemViewData(
                        id = it.brand.id,
                        korName = it.brand.korName,
                        engDescriptionBody = it.brand.engDescriptionBody,
                        korDescriptionBody = it.brand.korDescriptionBody,
                        relatedImgUrl = it.brand.relatedImgUrl,
                        engName = it.brand.engName,
                        engDescriptionTitle = it.brand.engDescriptionTitle,
                        korDescriptionTitle = it.brand.korDescriptionTitle,
                        imgUrl = it.brand.imgUrl
                    ) else null,
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