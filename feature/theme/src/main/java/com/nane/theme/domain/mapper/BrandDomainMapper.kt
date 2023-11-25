package com.nane.theme.domain.mapper

import com.nane.theme.domain.data.BrandsDomainDTO
import com.nane.theme.presentation.data.BrandViewData
import javax.inject.Inject

class BrandDomainMapper @Inject constructor() {

    fun toViewData(dto: BrandsDomainDTO): List<BrandViewData> {
        return dto.itemList
            .map {
                BrandViewData(
                    engTitle = it.engName,
                    korTitle = it.korName,
                    imageUrl = it.brandImgUrl,
                    id = it.id
                )
            }
    }
}