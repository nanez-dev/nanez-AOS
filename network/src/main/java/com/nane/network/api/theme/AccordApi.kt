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
        @SerializedName("eng") val engName: String,
        @SerializedName("kor") val korName: String,
        @SerializedName("image") val imageUrl: String,
        val code: Int,
        val id: Int
    )

    @Keep
    data class AccordDetail(
        @SerializedName("accord") val accordItem: AccordItem,
        @SerializedName("relative_perfumes") val relativePerfumes: List<RelativePerfume>
    )

    @Keep
    data class RelativePerfume(
        val id: Int,
        @SerializedName("kor") val korName: String,
        @SerializedName("eng") val engName: String,
        @SerializedName("is_single") val isSingle: Boolean,
        @SerializedName("image") val imageUrl: String,
        @SerializedName("brand_id") val brandId: Int,
        @SerializedName("density_id") val densityId: Int,
        @SerializedName("web_image1") val webImage1: String,
        val capacity: Int,
        val price: Int,
        val title: String,
        @SerializedName("subtitle") val description: String,
        val brand: BrandApi.BrandItem
    )
}