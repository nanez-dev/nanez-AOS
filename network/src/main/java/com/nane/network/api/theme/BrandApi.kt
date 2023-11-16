package com.nane.network.api.theme

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class BrandApi {

    data class Brands(
        val brands: List<BrandItem>?
    )

    @Keep
    data class BrandItem(
        val id: Int,
        @SerializedName("eng") val engTitle: String,
        @SerializedName("kor") val korTitle: String,
        @SerializedName("image") val imageUrl: String
    )
}