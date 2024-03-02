package com.nane.join.domain.mapper

import com.nane.join.domain.data.JoinAccordDTO
import com.nane.join.domain.data.JoinSignUpDTO
import com.nane.join.presentation.data.JoinAccordViewData
import com.nane.join.presentation.data.JoinUserViewData
import javax.inject.Inject

/**
 * Created by haul on 11/20/23
 */
class JoinDomainMapper @Inject constructor() {

    fun toAccordViewData(data: JoinAccordDTO): JoinAccordViewData {
        return JoinAccordViewData(
            id = data.id,
            name = data.name,
            imgUrl = data.imgUrl,
            isSelected = false
        )
    }

    fun toSignUpDto(data: JoinUserViewData): JoinSignUpDTO {
        return JoinSignUpDTO(
            nickname = data.nickName,
            email = data.email,
            password = data.password,
            isAccepted = data.isAccepted,
            referCode = data.referCode,
            accordId = data.accordId
        )
    }
}