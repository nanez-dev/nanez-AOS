package com.nane.theme.domain.data

data class AccordDetailDomainDTO(
    val accord: AccordDTO?,
    val relatedPerfumes: List<AccordPerfume>?
)
data class AccordPerfume(
    val id: Int,
    val brand: BrandDTO?,
    val brandId: Int,
    val isSingle: Boolean,
    val imgUrl: String?,
    val webImgUrl1: String?,
    val webImgUrl2: String?,
    val capacity: Int,
    val densityId: Int,
    val price: Int,
    val title: String?,
    val description: String
)