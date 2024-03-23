package com.nane.profile.data.mapper

import com.nane.network.api.users.ProfileApi
import com.nane.profile.domain.data.ProfileDTO
import javax.inject.Inject

/**
 * Created by haul on 3/2/24
 */
class ProfileDataMapper @Inject constructor() {

    fun toProfileDto(api: ProfileApi.Response?): ProfileDTO {
        return ProfileDTO(
            nickName = api?.nickname,
            email = api?.email,
            profileImageUrl = api?.profile_image,
        )
    }
}