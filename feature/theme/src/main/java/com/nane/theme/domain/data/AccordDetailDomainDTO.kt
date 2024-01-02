package com.nane.theme.domain.data

data class AccordDetailDomainDTO(
    val accord: AccordDTO?,
    val relatedPerfumes: List<PerfumeDomainDTO>?
)