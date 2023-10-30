package com.nane.network.api.users

import androidx.annotation.Keep

/**
 * Created by haul on 10/30/23
 */
@Keep
class JoinVerifyAuthEmailCodeApi {

    @Keep
    data class Body(
        val code: String,
        val email: String
    )
}