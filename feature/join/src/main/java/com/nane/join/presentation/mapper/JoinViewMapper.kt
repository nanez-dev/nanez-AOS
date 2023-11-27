package com.nane.join.presentation.mapper

import com.nane.join.domain.data.JoinAccordDTO
import com.nane.join.presentation.data.JoinAccordViewData
import javax.inject.Inject

/**
 * Created by haul on 11/20/23
 */
class JoinViewMapper @Inject constructor() {

    fun toAccordViewData(data: JoinAccordDTO): JoinAccordViewData {
        return JoinAccordViewData(
            id = data.id,
            name = data.name,
            imgUrl = data.imgUrl,
            isSelected = false
        )
    }
}