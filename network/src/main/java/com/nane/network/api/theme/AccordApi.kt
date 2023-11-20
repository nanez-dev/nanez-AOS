package com.nane.network.api.theme

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class AccordApi {

    data class Accords(
        val accords: List<AccordItem>?
    )

    @Keep
    data class AccordItem(
        @SerializedName("eng") val engTitle: String,
        @SerializedName("kor") val korTitle: String,
        @SerializedName("image") val imageUrl: String,
        val code: Int,
        val id: Int
    )
}