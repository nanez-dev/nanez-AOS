package com.nane.theme.presentation.mapper

import com.nane.theme.domain.data.BrandsDomainDTO
import com.nane.theme.presentation.data.BrandViewData
import javax.inject.Inject

class BrandDomainMapper @Inject constructor() {

    fun toViewData(dto: BrandsDomainDTO): List<BrandViewData> {
        return dto.itemList
            .map {
                BrandViewData(
                    engName = it.engName,
                    korName = it.korName,
                    imageUrl = it.brandImgUrl,
                    id = it.id
                )
            }
    }
}