package com.nane.network.api.theme

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class BrandApi {

    @Keep
    data class Brands(
        @SerializedName("popular_brands") val popularBrands: List<BrandItem>?,
        val brands: List<BrandItem>?
    )

    @Keep
    data class BrandItem(
        val id: Int,
        @SerializedName("kor") val korName: String,
        @SerializedName("eng_desc_body") val engDescriptionBody: String,
        @SerializedName("kor_desc_body") val korDescriptionBody: String,
        @SerializedName("rel_image") val relatedImgUrl: String,
        @SerializedName("eng") val engName: String,
        @SerializedName("eng_desc_title") val engDescriptionTitle: String,
        @SerializedName("kor_desc_title") val korDescriptionTitle: String,
        @SerializedName("image") val imgUrl: String,
    )

    @Keep
    data class BrandDetail(
        @SerializedName("brand") val brandItem: BrandItem,
        @SerializedName("related_perfumes") val relatedPerfumes: List<AccordApi.RelatedPerfume>?
    )
}