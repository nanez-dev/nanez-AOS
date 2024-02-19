package com.nane.network.api.home

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by iseungjun on 2023/08/26
 */
@Keep
class HomeApi {

    @Keep
    data class Response(
        @SerializedName("top_rolling_banner")
        val topBannerList: TopBanner?,

        @SerializedName("special_perfumes")
        val specialPerfumeList: SpecialPerfume?,

        @SerializedName("recommend_perfumes")
        val recommendPerfumeList: RecommendPerfume?,

        @SerializedName("popular_brands")
        val popularBrandList: PopularBrand?,

        @SerializedName("recommend_accords")
        val recommendAccordList: RecommendAccord?
    )

    @Keep
    data class TopBanner(
        val title: String?,
        val subTitle: String?,
        val list: List<BannerItem>?
    )

    @Keep
    data class BannerItem(
        val id: Int,
        val image: String?,
        val link: String?,
    )

    @Keep
    data class SpecialPerfume(
        val title: String?,
        val list: List<Perfume>?,
    )

    @Keep
    data class RecommendPerfume(
        val title: String?,
        val list: List<Perfume>?,
    )

    @Keep
    data class PopularBrand(
        val title: String?,
        val list: List<BrandItem>?
    )

    @Keep
    data class RecommendAccord(
        val title: String?,
        val list: List<AccordItem>?
    )

    @Keep
    data class Perfume(
        @SerializedName("kor")
        val name: String?,
        val image: String?,
        val price: String,
        val capacity: String?,
        @SerializedName("subtitle")
        val description: String?,
        val brand: BrandItem?,
    )

    @Keep
    data class BrandItem(
        val id: Int,
        @SerializedName("kor")
        val name: String?,
        val image: String?,
    )

    @Keep
    data class AccordItem(
        val id: Int,
        @SerializedName("kor")
        val name: String?,
        val image: String?,
    )
}