package com.nane.theme.domain.mapper

import com.nane.theme.domain.data.BrandDetailDomainDTO
import com.nane.theme.presentation.data.BrandDetailBaseViewData
import com.nane.theme.presentation.data.BrandDetailViewData
import com.nane.theme.presentation.data.BrandPerfumeViewData
import com.nane.theme.presentation.data.BrandViewData
import javax.inject.Inject

class BrandDetailDomainMapper @Inject constructor() {

    fun toViewData(dto: BrandDetailDomainDTO): BrandDetailViewData {
        return BrandDetailViewData(
            id = dto.id,
            engTitle = dto.engName,
            korTitle = dto.korName,
            imgUrl = dto.imgUrl,
            detail = if (dto.detail == null) null else BrandDetailBaseViewData(
                topTitle = dto.detail.topTitle,
                topSubTitle = dto.detail.topDescription,
                bottomTitle = dto.detail.bottomTitle,
                bottomSubTitle = dto.detail.bottomDescription,
                imgUrl1 = dto.detail.imgUrl1,
                imgUrl2 = dto.detail.imgUrl2,
                imgUrl3 = dto.detail.imgUrl3,
                imgUrl4 = dto.detail.imgUrl4,
                imgUrl5 = dto.detail.imgUrl5,
                id = dto.detail.id,
                brandId = dto.detail.brandId
            ),
            relativePerfumes = dto.relativePerfumes?.map {
                BrandPerfumeViewData(
                    id = it.id,
                    brand = if (it.brand == null) null else BrandViewData(
                        engTitle = it.brand.engName,
                        korTitle = it.brand.korName,
                        imageUrl = it.brand.brandImgUrl,
                        id = it.brand.id
                    ),
                    korTitle = it.korName,
                    engTitle = it.engName,
                    imgUrl = it.imgUrl,
                    capacity = it.capacity,
                    price = it.price
                )
            } ?: emptyList()
        )
    }
}