package com.nane.theme.data.mapper

import com.nane.network.api.theme.AccordApi
import com.nane.theme.domain.data.AccordDTO
import com.nane.theme.domain.data.AccordDetailDomainDTO
import com.nane.theme.domain.data.AccordsDomainDTO
import com.nane.theme.domain.data.BrandDTO
import com.nane.theme.domain.data.PerfumeDomainDTO
import javax.inject.Inject

class AccordDataMapper @Inject constructor() {

    fun toDTO(apiData: AccordApi.Accords?): AccordsDomainDTO {
        return AccordsDomainDTO(
            popularAccords = apiData?.popularAccords?.map {
                AccordDTO(
                    engName = it.engName,
                    korName = it.korName,
                    imgUrl = it.imgUrl,
                    korDescriptionBody = it.description,
                    code = it.code,
                    id = it.id,
                )
            } ?: emptyList(),
            allAccords = apiData?.accords?.map {
                AccordDTO(
                    engName = it.engName,
                    code = it.code,
                    engDescriptionTitle = it.engDescriptionTitle,
                    korDescriptionTitle = it.korDescriptionTitle,
                    imgUrl = it.imgUrl,
                    relatedImgUrl = it.relatedImgUrl,
                    id = it.id,
                    korName = it.korName,
                    engDescriptionBody = it.engDescriptionBody,
                    korDescriptionBody = it.korDescriptionBody
                ) } ?: emptyList()
        )
    }

    fun toDetailDTO(apiData: AccordApi.AccordDetail?): AccordDetailDomainDTO {
        return AccordDetailDomainDTO(
            accord = if (apiData?.accordItem != null) AccordDTO(
                engName = apiData.accordItem.engName,
                korName = apiData.accordItem.korName,
                imgUrl = apiData.accordItem.imgUrl,
                code = apiData.accordItem.code,
                id = apiData.accordItem.id,
                korDescriptionTitle = apiData.accordItem.korDescriptionTitle,
                korDescriptionBody = apiData.accordItem.korDescriptionBody,
                relatedImgUrl = apiData.accordItem.relatedImgUrl
            ) else null,
            relatedPerfumes = apiData?.relatedPerfumes?.map {
                PerfumeDomainDTO(
                    korName = it.korName,
                    engName = it.engName,
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
