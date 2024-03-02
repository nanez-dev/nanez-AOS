package com.nane.profile.domain.mapper

import com.nane.profile.domain.data.ProfileDTO
import com.nane.profile.presentation.data.ProfileLoginViewData
import javax.inject.Inject

/**
 * Created by haul on 3/2/24
 */
class ProfileDomainMapper @Inject constructor() {

    fun toViewData(dto: ProfileDTO): ProfileLoginViewData {
        return ProfileLoginViewData(
            nickName = dto.nickName,
            email = dto.email,
            profileImageUrl = dto.profileImageUrl,
            wishCount = 0,
            havingCount = 0
        )
    }
}