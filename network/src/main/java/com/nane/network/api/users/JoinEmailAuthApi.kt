package com.nane.network.api.users

import androidx.annotation.Keep

/**
 * Created by haul on 10/30/23
 */
@Keep
class JoinEmailAuthApi {

    @Keep
    data class Body(
        val email: String
    )
}