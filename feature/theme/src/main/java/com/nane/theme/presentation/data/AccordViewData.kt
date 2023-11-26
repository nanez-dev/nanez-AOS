package com.nane.theme.presentation.data

data class AccordViewData(
    val popularAccords: List<AccordItemViewData>,
    val allAccords: List<AccordItemViewData>
)

data class AccordItemViewData(
    val engName: String?,
    val korName: String?,
    val imgUrl: String?,
    val description: String? = null,
    val id: Int
)