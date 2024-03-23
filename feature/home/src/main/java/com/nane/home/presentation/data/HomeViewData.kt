package com.nane.home.presentation.data

/**
 * Created by iseungjun on 2023/08/19
 */

sealed class HomeViewData(@HomeViewType var viewType: Int) {

    data class Banner(
        val bannerList: List<BannerItem> = emptyList()
    ) : HomeViewData(HomeViewType.HOME_PAGER_TYPE) {

        data class BannerItem(
            val imgUrl: String?,
            val link: String?,
        )
    }


    data class MainTitle(
        val title: String?,
        val content: String?,
    ) : HomeViewData(HomeViewType.HOME_TITLE_TYPE)


    data class SpecialPerfume(
        val title: String?,
        val itemList: List<PerfumeItemViewData> = emptyList(),
    ) : HomeViewData(HomeViewType.HOME_HORI_LIST_TYPE)


    data class RecommondPerfume(
        val title: String?,
        val itemList: List<PerfumeItemViewData> = emptyList(),
    ) : HomeViewData(HomeViewType.HOME_RECOMMEND_TYPE)


    data class Brand(
        val title: String?,
        val itemList: List<BrandItem> = emptyList(),
    ) : HomeViewData(HomeViewType.HOME_BRAND_TYPE) {

        data class BrandItem(
            val id: Int,
            val imgUrl: String?,
            val brandName: String?
        )
    }


    data class Accord(
        val title: String?,
        val itemList: List<AccordItem> = emptyList(),
    ) : HomeViewData(HomeViewType.HOME_ACCORD_TYPE) {

        data class AccordItem(
            val id: Int,
            val imgUrl: String?,
            val accordName: String?
        )
    }

}


data class PerfumeItemViewData(
    val imgUrl: String?,
    val brandName: String?,
    val name: String?,
    val capacity: String?,
    val content: String? = null,
    val id: Int,
)
