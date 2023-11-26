package com.nane.theme.data.mapper

import com.nane.network.api.theme.AccordApi
import com.nane.theme.domain.data.AccordDTO
import com.nane.theme.domain.data.AccordDetailDomainDTO
import com.nane.theme.domain.data.AccordPerfume
import com.nane.theme.domain.data.BrandDTO
import org.techtown.nanez.utils.NaneLog
import javax.inject.Inject

class AccordDetailDataMapper @Inject constructor() {
    fun toDTO(apiData: AccordApi.AccordDetail?): AccordDetailDomainDTO {
        return AccordDetailDomainDTO(
            accord = if (apiData?.accordItem != null) AccordDTO(
                engName = apiData.accordItem.engName,
                korName = apiData.accordItem.korName,
                imgUrl = apiData.accordItem.imgUrl,
                code = apiData.accordItem.code,
                id = apiData.accordItem.id,
            ) else null,
            relatedPerfumes = apiData?.relativePerfumes?.map {
                AccordPerfume(
                    korName = it.korName,
                    engName = it.engName,
                    id = it.id,
                    brand = BrandDTO(
                        id = it.brand.id,
                        engName = it.brand.engName,
                        korName = it.brand.korName,
                        brandImgUrl = it.brand.imgUrl
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
                    description = it.description
                )
            } ?: emptyList()
        )
    }
}
