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
        @SerializedName("eng") val engName: String,
        @SerializedName("kor") val korName: String,
        @SerializedName("image") val imgUrl: String
    )

    data class BrandDetail(
        val id: Int,
        @SerializedName("eng") val engName: String,
        @SerializedName("kor") val korName: String,
        @SerializedName("image") val brandImgUrl: String,
        val detail: Detail,
        @SerializedName("relative_perfumes") val relativePerfumes: List<RelativePerfume>?
    )

    @Keep
    data class Detail(
        @SerializedName("top_title") val topTitle: String,
        @SerializedName("top_subtitle") val topSubTitle: String,
        @SerializedName("bottom_title") val bottomTitle: String,
        @SerializedName("bottom_subtitle") val bottomSubTitle: String,
        @SerializedName("image1") val imageUrl1: String,
        @SerializedName("image2") val imageUrl2: String,
        @SerializedName("image3") val imageUrl3: String,
        @SerializedName("image4") val imageUrl4: String,
        @SerializedName("image5") val imageUrl5: String,
        val id: Int,
        val brandId: Int,
    )

    @Keep
    data class RelativePerfume(
        val id: Int,
        val brand: BrandItem,
        @SerializedName("eng") val engName: String,
        @SerializedName("kor") val korName: String,
        @SerializedName("image") val imgUrl: String,
        val capacity: Int,
        val price: Int
    )
}