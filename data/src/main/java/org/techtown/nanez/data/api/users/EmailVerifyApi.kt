package org.techtown.nanez.data.api.users

import androidx.annotation.Keep

/**
 * Created by iseungjun on 2023/08/17
 */
@Keep
class EmailVerifyApi {

    @Keep
    data class Body(
        val code: Int,
        val email: String,
    )
}