package com.nane.join.data.mapper

import com.nane.join.domain.data.JoinAccordDTO
import com.nane.network.api.theme.AccordApi
import javax.inject.Inject

/**
 * Created by haul on 11/20/23
 */
class JoinDataMapper @Inject constructor() {

    fun toAccordDTO(data: AccordApi.AccordItem): JoinAccordDTO {
        return JoinAccordDTO(
            id = data.id,
            name = data.korTitle,
            imgUrl = data.imageUrl
        )
    }
}