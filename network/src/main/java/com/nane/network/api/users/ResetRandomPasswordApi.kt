package com.nane.network.api.users

import androidx.annotation.Keep

/**
 * Created by haul on 3/25/24
 */
@Keep
class ResetRandomPasswordApi {

    @Keep
    data class Body(
        val email: String
    )
}