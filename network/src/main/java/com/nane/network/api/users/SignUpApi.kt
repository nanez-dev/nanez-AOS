package com.nane.network.api.users

import androidx.annotation.Keep

/**
 * Created by iseungjun on 2023/08/17
 */
@Keep
class SignUpApi {
    /**
     * {
     *   "nickname": "test_minho",
     *   "email": "test_minho@gmail.com",
     *   "password": "string",
     *   "gender": "-",
     *   "age_group": null,
     *   "refer_code": "string",
     *   "accord_id": 1,
     *   "is_accepted": true,
     *   "profile_image": null
     * }
     */

    @Keep
    data class Body(
        val nickname: String?,
        val email: String?,
        val password: String?,
        val refer_code: String?,
        val accord_id: Int,
        val is_accepted: Boolean,
        val profile_image: String?,
        val gender: String?,
    )

    @Keep
    data class Response(
        val access_token: String?,
        val refresh_token: String?,
    )
}