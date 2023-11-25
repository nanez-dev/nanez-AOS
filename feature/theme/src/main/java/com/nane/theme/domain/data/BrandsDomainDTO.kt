package com.nane.theme.domain.data

data class BrandsDomainDTO(
    val itemList: List<BrandDTO>
)

data class BrandDTO(
    val engName: String?,
    val korName: String?,
    val brandImgUrl: String?,
    val id: Int
)