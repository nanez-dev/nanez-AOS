package org.techtown.nanez.data.api.home

import androidx.annotation.Keep

/**
 * Created by iseungjun on 2023/08/26
 */
@Keep
class HomeApi {

    @Keep
    data class Response(
        val imgList: List<String>?,
        val mainTitle: MainTitle?,
        val horizontal: Horizontal?,
        val recommend: Recommend?,
        val brand: Brand?,
        val accord: Accord?
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
        val name: String?,
        val imgUrl: String?,
        val brandName: String?,
        val volume: String?,
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