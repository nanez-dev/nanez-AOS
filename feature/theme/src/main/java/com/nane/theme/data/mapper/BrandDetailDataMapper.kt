package com.nane.theme.data.mapper

import com.nane.network.api.theme.BrandApi
import com.nane.theme.domain.data.BrandDTO
import com.nane.theme.domain.data.BrandDetailBase
import com.nane.theme.domain.data.BrandDetailDomainDTO
import com.nane.theme.domain.data.BrandPerfume
import javax.inject.Inject

class BrandDetailDataMapper @Inject constructor() {
    fun toDTO(apiData: BrandApi.BrandDetail?): BrandDetailDomainDTO {
        return BrandDetailDomainDTO(
            id = apiData?.id ?: 1,
            engName = apiData?.engName,
            korName = apiData?.korName,
            imgUrl = apiData?.brandImgUrl,
            detail = if (apiData?.detail == null) null else BrandDetailBase(
                topTitle = apiData.detail.topTitle,
                topDescription = apiData.detail.topSubTitle,
                bottomTitle = apiData.detail.bottomTitle,
                bottomDescription = apiData.detail.bottomSubTitle,
                imgUrl1 = apiData.detail.imageUrl1,
                imgUrl2 = apiData.detail.imageUrl2,
                imgUrl3 = apiData.detail.imageUrl3,
                imgUrl4 = apiData.detail.imageUrl4,
                imgUrl5 = apiData.detail.imageUrl5,
                id = apiData.detail.id,
                brandId = apiData.detail.brandId
            ),
            relativePerfumes = apiData?.relativePerfumes?.map {
                BrandPerfume(
                    id = it.id,
                    brand = BrandDTO(
                        engName = it.brand.engName,
                        korName = it.brand.korName,
                        brandImgUrl = it.brand.imgUrl,
                        id = it.brand.id
                    ),
                    korName = it.korName,
                    engName = it.engName,
                    imgUrl = it.imgUrl,
                    capacity = it.capacity,
                    price = it.price
                )
            }
        )
    }
}
