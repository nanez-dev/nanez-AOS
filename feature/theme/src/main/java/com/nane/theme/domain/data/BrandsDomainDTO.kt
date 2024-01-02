package com.nane.theme.domain.data

data class BrandsDomainDTO(
    val popularBrands: List<BrandDTO>,
    val allBrands: List<BrandDTO>
)

data class BrandDTO(
    val id: Int,
    val korName: String?,
    val engDescriptionBody: String?,
    val korDescriptionBody: String?,
    val relatedImgUrl: String?,
    val engName: String?,
    val engDescriptionTitle: String?,
    val korDescriptionTitle: String?,
    val imgUrl: String?
)