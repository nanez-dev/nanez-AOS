package com.nane.theme.domain.data

data class BrandDetailDomainDTO(
    val id: Int,
    val engName: String?,
    val korName: String?,
    val imgUrl: String?,
    val detail: BrandDetailBase?,
    val relativePerfumes: List<BrandPerfume>?
)

data class BrandDetailBase(
    val topTitle: String?,
    val topDescription: String?,
    val bottomTitle: String?,
    val bottomDescription: String?,
    val imgUrl1: String?,
    val imgUrl2: String?,
    val imgUrl3: String?,
    val imgUrl4: String?,
    val imgUrl5: String?,
    val id: Int,
    val brandId: Int
)

data class BrandPerfume(
    val id: Int,
    val brand: BrandDTO?,
    val korName: String?,
    val engName: String?,
    val imgUrl: String?,
    val capacity: Int,
    val price: Int
)