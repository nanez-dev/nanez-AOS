package com.nane.theme.domain.data

data class BrandsDomainDTO(
    val itemList: List<BrandDTO>
)

data class BrandDTO(
    val engTitle: String?,
    val korTitle: String?,
    val imageUrl: String?,
    val id: Int?
)