package com.nane.theme.presentation.data

data class PerfumeViewData(
    val korName: String?,
    val engName: String?,
    val id: Int,
    val brand: BrandItemViewData?,
    val brandId: Int,
    val isSingle: Boolean,
    val imgUrl: String?,
    val webImgUrl1: String?,
    val webImgUrl2: String?,
    val capacity: Int,
    val densityId: Int,
    val price: Int,
    val title: String?,
    val rating: Float,
    val description: String
)