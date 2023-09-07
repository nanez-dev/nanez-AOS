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
        val topBannerList: List<TopBanner>?,
        val mainTitle: MainTitle?,
        val horizontal: Horizontal?,
        @SerializedName("main_perfumes")
        val mainPerfumeList: Recommend?,
        val brand: Brand?,
        val accord: Accord?
    )

    @Keep
    data class TopBanner(
        val id: Int,
        val image: String?,
        val link: String?,
    )

    @Keep
    data class MainTitle(
        val title: String?,
        val subTitle: String?
    )

    @Keep
    data class Horizontal(
        val title: String?,
        val itemList: List<Perfume>?,
    )

    @Keep
    data class Recommend(
        val title: String?,
        val itemList: List<Perfume>?,
    )

    @Keep
    data class Brand(
        val title: String?,
        val itemList: List<BrandItem>?
    )

    @Keep
    data class Accord(
        val title: String?,
        val itemList: List<AccordItem>?
    )

    @Keep
    data class Perfume(
        @SerializedName("kor")
        val name: String?,
        val image: String?,
        val brandName: String?,
        val volume: String?,
        @SerializedName("subtitle")
        val description: String?,
    )

    @Keep
    data class BrandItem(
        val name: String?,
        val imgUrl: String?,
    )
    @Keep
    data class AccordItem(
        val name: String?,
        val imgUrl: String?,
    )
}