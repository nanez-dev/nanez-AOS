package com.nane.theme.data.mapper

import com.nane.network.api.theme.AccordApi
import com.nane.theme.domain.data.AccordDTO
import com.nane.theme.domain.data.AccordsDomainDTO
import javax.inject.Inject

class AccordsDataMapper @Inject constructor() {
    fun toDTO(apiData: AccordApi.Accords?): AccordsDomainDTO {
        return AccordsDomainDTO(
            itemList = apiData?.accords?.map {
                AccordDTO(
                    engTitle = it.engTitle,
                    korTitle = it.korTitle,
                    imageUrl = it.imageUrl,
                    code = it.code,
                    id = it.id
                ) } ?: emptyList()
        )
    }
}
