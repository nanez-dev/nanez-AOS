package com.nane.theme.domain.data

data class AccordsDomainDTO(
    val itemList: List<AccordDTO>
)

data class AccordDTO(
    val engTitle: String?,
    val korTitle: String?,
    val imageUrl: String?,
    val code: Int,
    val id: Int
)