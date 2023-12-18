package com.nane.theme.presentation.data

data class BrandViewData(
    val popularBrands: List<BrandItemViewData>,
    val allBrands: List<BrandItemViewData>
)

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