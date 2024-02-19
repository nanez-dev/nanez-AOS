package com.nane.theme.presentation.data

sealed class BrandViewData(
    @BrandViewType var viewType: Int
) {
    data class PopularBrandItemListViewData(
        var brandItemList: List<BrandItemViewData>
    ): BrandViewData(BrandViewType.POPULAR_ITEM_LIST_TYPE)

    data class AllBrandItemViewData(
        val id: Int,
        val korName: String?,
        val engDescriptionBody: String? = null,
        val korDescriptionBody: String?,
        val relatedImgUrl: String?,
        val engName: String?,
        val engDescriptionTitle: String? = null,
        val korDescriptionTitle: String?,
        val imgUrl: String?
    ): BrandViewData(BrandViewType.ALL_BRAND_ITEM_TYPE)

    object BrandTitleViewData: BrandViewData(BrandViewType.TITLE_TYPE)
}

data class BrandItemViewData(
    val id: Int,
    val korName: String?,
    val engDescriptionBody: String? = null,
    val korDescriptionBody: String?,
    val relatedImgUrl: String?,
    val engName: String?,
    val engDescriptionTitle: String? = null,
    val korDescriptionTitle: String?,
    val imgUrl: String?
)