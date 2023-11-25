package com.nane.theme.domain.mapper

import com.nane.theme.domain.data.AccordsDomainDTO
import com.nane.theme.presentation.data.AccordViewData
import javax.inject.Inject

class AccordDomainMapper @Inject constructor() {

    fun toViewData(dto: AccordsDomainDTO): List<AccordViewData> {
        return dto.itemList
            .map {
                AccordViewData(
                    engTitle = it.engName,
                    korTitle = it.korName,
                    imageUrl = it.imgUrl,
                    id = it.id
                )
            }
    }
}