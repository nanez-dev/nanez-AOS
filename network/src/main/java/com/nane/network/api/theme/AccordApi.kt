package com.nane.network.api.theme

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class AccordApi {

    data class Accords(
        @SerializedName("popular_accords") val popularAccords: List<PopularAccordItem>?,
        val accords: List<AccordItem>?
    )

    @Keep
    data class PopularAccordItem(
        @SerializedName("eng") val engName: String,
        @SerializedName("kor") val korName: String,
        @SerializedName("image") val imgUrl: String,
        @SerializedName("desc") val description: String,
        val code: Int,
        val id: Int
    )

    @Keep
    data class AccordItem(
        @SerializedName("eng") val engName: String,
        @SerializedName("kor") val korName: String,
        @SerializedName("image") val imgUrl: String,
        val code: Int,
        val id: Int
    )

    @Keep
    data class AccordDetail(
        @SerializedName("accord") val accordItem: AccordItem,
        @SerializedName("related_perfumes") val relativePerfumes: List<RelativePerfume>
    )

    @Keep
    data class RelativePerfume(
        val id: Int,
        @SerializedName("kor") val korName: String,
        @SerializedName("eng") val engName: String,
        @SerializedName("is_single") val isSingle: Boolean,
        @SerializedName("image") val imgUrl: String,
        @SerializedName("brand_id") val brandId: Int,
        @SerializedName("density_id") val densityId: Int,
        @SerializedName("web_image1") val webImgUrl1: String,
        @SerializedName("web_image2") val webImgUrl2: String,
        val capacity: Int,
        val price: Int,
        val title: String,
        @SerializedName("subtitle") val description: String,
        val brand: BrandApi.BrandItem
    )
}