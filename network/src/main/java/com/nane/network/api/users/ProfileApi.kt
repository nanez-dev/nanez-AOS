package com.nane.network.api.users

import androidx.annotation.Keep

/**
 * Created by haul on 3/2/24
 * {
 *   "id": 3,
 *   "nickname": "devamos",
 *   "email": "minho.lee0716@gmail.com",
 *   "gender": "M",
 *   "age_group": 20,
 *   "profile_image": "https://d2r80qr1d3owbg.cloudfront.net/accord/CE4497DDC98146E4965843F60CA5AADB.png",
 *   "is_admin": false
 * }
 */
@Keep
class ProfileApi {

    @Keep
    data class Response(
        val id: Int,
        val nickname: String?,
        val email: String?,
        val gender: String?,
        val age_group: Int,
        val profile_image: String?,
        val is_admin: Boolean,
    )
}