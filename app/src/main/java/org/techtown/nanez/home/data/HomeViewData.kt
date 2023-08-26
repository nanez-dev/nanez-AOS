package org.techtown.nanez.home.data

/**
 * Created by iseungjun on 2023/08/19
 */

sealed class HomeViewData(@HomeViewType var viewType: Int) {

    data class HomeBannerData(
        val bannerList: List<String> = emptyList()
    ) : HomeViewData(HomeViewType.HOME_PAGER_TYPE)


    data class HomeTitleData(
        val title: String?,
        val content: String?,
    ) : HomeViewData(HomeViewType.HOME_TITLE_TYPE)


    data class HomeHorizontalData(
        val title: String?,
        val itemList: List<HomePerfumeItemViewData> = emptyList(),
    ) : HomeViewData(HomeViewType.HOME_HORI_LIST_TYPE)


    data class HomeRecommendPerfumeData(
        val title: String?,
        val itemList: List<HomePerfumeItemViewData> = emptyList(),
    ) : HomeViewData(HomeViewType.HOME_RECOMMEND_TYPE)


    data class HomeBrandData(
        val title: String?,
        val itemList: List<HomeBrandItemData> = emptyList(),
    ) : HomeViewData(HomeViewType.HOME_BRAND_TYPE) {

        data class HomeBrandItemData(
            val imgUrl: String?,
            val brandName: String?
        )
    }


    data class HomeAccordData(
        val title: String?,
        val itemList: List<HomeAccordItemData> = emptyList(),
    ) : HomeViewData(HomeViewType.HOME_ACCORD_TYPE) {

        data class HomeAccordItemData(
            val imgUrl: String?,
            val accordName: String?
        )
    }

}


data class HomePerfumeItemViewData(
    val imgUrl: String?,
    val brandName: String?,
    val name: String?,
    val volume: String?,
    val content: String? = null,
)
