package com.nane.theme.presentation.data

sealed class AccordViewData(
    @AccordViewType var viewType: Int
) {
    data class PopularAccordItemListViewData(
        var accordItemList: List<AccordItemViewData>
    ): AccordViewData(AccordViewType.POPULAR_ITEM_LIST_TYPE)

    data class AllAccordItemViewData(
        val id: Int,
        val korName: String?,
        val engDescriptionBody: String? = null,
        val korDescriptionBody: String?,
        val relatedImgUrl: String?,
        val engName: String?,
        val engDescriptionTitle: String? = null,
        val korDescriptionTitle: String?,
        val imgUrl: String?
    ): AccordViewData(AccordViewType.ALL_ACCORD_ITEM_TYPE)

    object AccordTitleViewData: AccordViewData(AccordViewType.TITLE_TYPE)
}

data class AccordItemViewData(
    val engName: String?,
    val korName: String?,
    val imgUrl: String?,
    val engDescriptionTitle: String? = null,
    val korDescriptionTitle: String?,
    val engDescriptionBody: String? = null,
    val korDescriptionBody: String?,
    val id: Int,
    val code: Int,
    val relatedImgUrl: String?
)