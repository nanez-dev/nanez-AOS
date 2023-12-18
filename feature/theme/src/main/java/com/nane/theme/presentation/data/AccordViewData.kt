package com.nane.theme.presentation.data

data class AccordViewData(
    val popularAccords: List<AccordItemViewData>,
    val allAccords: List<AccordItemViewData>
)

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