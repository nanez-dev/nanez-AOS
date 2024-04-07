package com.nane.network.api.users

import androidx.annotation.Keep


@Keep
class WithdrawalApi() {

    @Keep
    data class Body(
        val detail: String,
    )
}
