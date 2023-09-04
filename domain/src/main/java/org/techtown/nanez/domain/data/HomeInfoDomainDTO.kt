package org.techtown.nanez.domain.data

/**
 * Created by iseungjun on 2023/08/26
 */
data class HomeInfoDomainDTO(
    val bannerList: List<HomeTopBannerDTO>,
    val mainTitle: HomeMainTitleDTO?,
    val horizontalInfo: HomeHorizontalDTO?,
    val recommendInfo: HomeRecommendDTO?,
    val brandInfo: HomeBrandDTO?,
    val accordInfo: HomeAccordDTO?,
)

data class HomeTopBannerDTO(
    val imgUrl: String?,
    val linkUrl: String?
)

data class HomeMainTitleDTO(
    val title: String?,
    val content: String?,
)

data class HomeHorizontalDTO(
    val title: String?,
    val itemList: List<HomePerfumeDTO> = emptyList(),
)

data class HomeRecommendDTO(
    val title: String?,
    val itemList: List<HomePerfumeDTO> = emptyList(),
)

data class HomeBrandDTO(
    val title: String?,
    val itemList: List<HomeBrandItemDTO> = emptyList(),
) {
    data class HomeBrandItemDTO(
        val name: String?,
        val imgUrl: String?,
    )
}

data class HomeAccordDTO(
    val title: String?,
    val itemList: List<HomeAccordItemDTO> = emptyList(),
) {
    data class HomeAccordItemDTO(
        val name: String?,
        val imgUrl: String?,
    )
}


data class HomePerfumeDTO(
    val imgUrl: String?,
    val name: String?,
    val brandName: String?,
    val volume: String?,
    val content: String?
)


