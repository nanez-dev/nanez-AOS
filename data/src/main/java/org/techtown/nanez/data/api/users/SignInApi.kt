package org.techtown.nanez.data.api.users

import androidx.annotation.Keep

/**
 * Created by iseungjun on 2023/08/17
 */
@Keep
class SignInApi {
    @Keep
    data class Body(
        val email: String,
        val password: String,
    )

    @Keep
    data class Response(
        val access_token: String,
        val refresh_token: String,
    )
}