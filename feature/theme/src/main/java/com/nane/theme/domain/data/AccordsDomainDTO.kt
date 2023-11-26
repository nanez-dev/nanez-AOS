package com.nane.theme.domain.data

data class AccordsDomainDTO(
    val popularAccords: List<AccordDTO>,
    val allAccords: List<AccordDTO>
)

data class AccordDTO(
    val engName: String?,
    val korName: String?,
    val imgUrl: String?,
    val description: String? = null,
    val code: Int,
    val id: Int
)