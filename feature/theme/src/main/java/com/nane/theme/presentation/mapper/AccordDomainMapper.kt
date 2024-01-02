package com.nane.theme.presentation.mapper

import com.nane.theme.domain.data.AccordsDomainDTO
import com.nane.theme.presentation.data.AccordItemViewData
import com.nane.theme.presentation.data.AccordViewData
import javax.inject.Inject

class AccordDomainMapper @Inject constructor() {

    fun toViewData(dto: AccordsDomainDTO): AccordViewData {
        return AccordViewData(
            popularAccords = dto.popularAccords.map {
                AccordItemViewData(
                    engName = it.engName,
                    korName = it.korName,
                    imgUrl = it.imgUrl,
                    id = it.id,
                    korDescriptionTitle = it.korDescriptionTitle,
                    korDescriptionBody = it.korDescriptionBody,
                    code = it.code,
                    relatedImgUrl = it.relatedImgUrl
                )
            },
            allAccords = dto.allAccords.map {
                AccordItemViewData(
                    engName = it.engName,
                    korName = it.korName,
                    imgUrl = it.imgUrl,
                    id = it.id,
                    korDescriptionTitle = it.korDescriptionTitle,
                    korDescriptionBody = it.korDescriptionBody,
                    code = it.code,
                    relatedImgUrl = it.relatedImgUrl
                )
            }
        )
    }
}