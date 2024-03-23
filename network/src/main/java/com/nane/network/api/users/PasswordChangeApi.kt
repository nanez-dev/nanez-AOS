package com.nane.network.api.users

import androidx.annotation.Keep

@Keep
class PasswordChangeApi {
    @Keep
    data class Body(
        val current_password: String,
        val new_password: String,
    )
}
