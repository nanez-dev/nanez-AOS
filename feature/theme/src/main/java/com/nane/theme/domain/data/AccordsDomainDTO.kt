package com.nane.theme.domain.data

data class AccordsDomainDTO(
    val itemList: List<AccordDTO>
)

data class AccordDTO(
    val engName: String?,
    val korName: String?,
    val imgUrl: String?,
    val code: Int,
    val id: Int
)