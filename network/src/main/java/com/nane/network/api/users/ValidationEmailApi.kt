package org.techtown.nanez.data.api.users

import androidx.annotation.Keep

/**
 * Created by iseungjun on 2023/08/17
 */
@Keep
class ValidationEmailApi {

    @Keep
    data class Body(
        val email: String,
    )
}