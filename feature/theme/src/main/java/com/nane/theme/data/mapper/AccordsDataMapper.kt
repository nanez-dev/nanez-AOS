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
                    engName = it.engName,
                    korName = it.korName,
                    imgUrl = it.imageUrl,
                    code = it.code,
                    id = it.id
                ) } ?: emptyList()
        )
    }
}
