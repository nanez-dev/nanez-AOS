package com.nane.theme.domain.data

data class AccordsDomainDTO(
    val popularAccords: List<AccordDTO>,
    val allAccords: List<AccordDTO>
)

data class AccordDTO(
    val engName: String? = null,
    val code: Int,
    val engDescriptionTitle: String? = null,
    val korDescriptionTitle: String? = null,
    val imgUrl: String? = null,
    val relatedImgUrl: String? = null,
    val id: Int,
    val korName: String? = null,
    val engDescriptionBody: String? = null,
    val korDescriptionBody: String? = null
)


data class AccordDetailDomainDTO(
    val accord: AccordDTO?,
    val relatedPerfumes: List<PerfumeDomainDTO>?
)