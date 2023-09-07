package org.techtown.nanez.data.api.users

import androidx.annotation.Keep

/**
 * Created by iseungjun on 2023/08/17
 */
@Keep
class SignUpApi {

    /**
     * "nickname": "string",
     *   "email": "string",
     *   "password": "string",
     *   "gender": "string",
     *   "age_group": 0,
     *   "refer_code": "string",
     *   "accord_id": 0,
     *   "is_accepted": true,
     *   "profile_image": "string"
     */
    @Keep
    data class Body(
        val nickname: String,
        val email: String,
        val password: String,
        val gender: String,
        val age_group: Int,
        val refer_code: String,
        val accord_id: Int,
        val is_accepted: Boolean,
        val profile_image: String
    )

    @Keep
    data class Response(
        val access_token: String,
        val refresh_token: String,
    )
}