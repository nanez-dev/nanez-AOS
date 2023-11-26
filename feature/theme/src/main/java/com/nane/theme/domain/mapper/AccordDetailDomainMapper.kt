package com.nane.theme.domain.mapper

import com.nane.theme.domain.data.AccordDetailDomainDTO
import com.nane.theme.presentation.data.AccordDetailViewData
import com.nane.theme.presentation.data.AccordItemViewData
import com.nane.theme.presentation.data.AccordPerfumeViewData
import com.nane.theme.presentation.data.BrandViewData
import javax.inject.Inject

class AccordDetailDomainMapper @Inject constructor() {

    fun toViewData(dto: AccordDetailDomainDTO): AccordDetailViewData {
        return AccordDetailViewData(
            accordItemViewData = if (dto.accord != null) AccordItemViewData(
                engName = dto.accord.engName,
                korName = dto.accord.korName,
                imgUrl = dto.accord.imgUrl,
                id = dto.accord.id
            ) else null,
            relatedPerfumes = dto.relatedPerfumes?.map {
                AccordPerfumeViewData(
                    korName = it.korName,
                    engName = it.engName,
                    id = it.id,
                    brand = if (it.brand != null) BrandViewData(
                        engName = it.brand.engName,
                        korName = it.brand.korName,
                        imageUrl = it.brand.brandImgUrl,
                        id = it.brand.id
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
                    description = it.description
                )
            } ?: emptyList()
        )
    }
}