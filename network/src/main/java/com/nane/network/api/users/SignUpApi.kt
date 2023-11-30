package com.nane.network.api.users

import androidx.annotation.Keep

/**
 * Created by iseungjun on 2023/08/17
 */
@Keep
class SignUpApi {

    @Keep
    data class Body(
        val nickname: String?,
        val email: String?,
        val password: String?,
        val refer_code: String?,
        val accord_id: Int,
        val is_accepted: Boolean,
    )

    @Keep
    data class Response(
        val access_token: String?,
        val refresh_token: String?,
    )
}