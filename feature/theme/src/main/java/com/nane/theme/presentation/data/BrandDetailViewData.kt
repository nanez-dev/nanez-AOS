package com.nane.theme.presentation.data

data class BrandDetailViewData(
    val id: Int,
    val engTitle: String?,
    val korTitle: String?,
    val imgUrl: String?,
    val detail: BrandDetailBaseViewData?,
    val relatedPerfumes: List<BrandPerfumeViewData>
)

data class BrandDetailBaseViewData(
    val topTitle: String?,
    val topSubTitle: String?,
    val bottomTitle: String?,
    val bottomSubTitle: String?,
    val imgUrl1: String?,
    val imgUrl2: String?,
    val imgUrl3: String?,
    val imgUrl4: String?,
    val imgUrl5: String?,
    val id: Int,
    val brandId: Int
)

data class BrandPerfumeViewData(
    val id: Int,
    val brand: BrandViewData?,
    val korTitle: String?,
    val engTitle: String?,
    val imgUrl: String?,
    val capacity: Int,
    val price: Int
)