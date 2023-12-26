package com.nane.join.data.mapper

import com.nane.join.domain.data.JoinAccordDTO
import com.nane.join.domain.data.JoinSignUpDTO
import com.nane.network.api.theme.AccordApi
import com.nane.network.api.users.SignUpApi
import javax.inject.Inject

/**
 * Created by haul on 11/20/23
 */
class JoinDataMapper @Inject constructor() {

    fun toAccordDTO(data: AccordApi.AccordItem): JoinAccordDTO {
        return JoinAccordDTO(
            id = data.id,
            name = data.korName,
            imgUrl = data.imgUrl
        )
    }

    fun toBody(data: JoinSignUpDTO): SignUpApi.Body {
        return SignUpApi.Body(
            nickname = data.nickname,
            email = data.email,
            password = data.password,
            refer_code = data.referCode,
            is_accepted = data.isAccepted,
            accord_id = data.accordId,
            profile_image = null,
            gender = "-"
        )
    }
}