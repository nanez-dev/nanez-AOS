package com.nane.theme.domain.data

data class BrandDetailDomainDTO(
    val brand: BrandDTO?,
    val relatedPerfumes: List<PerfumeDomainDTO>?
)