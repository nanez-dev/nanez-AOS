package com.nane.profile.presentation.data

/**
 * Created by haul on 2/19/24
 */
data class ProfileLoginViewData(
    val nickName: String?,
    val email: String?,
    val profileImageUrl: String?,
    var wishCount: Int,
    var havingCount: Int,
)
