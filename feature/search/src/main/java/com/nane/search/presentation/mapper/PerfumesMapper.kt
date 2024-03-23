package com.nane.search.presentation.mapper

import com.nane.search.domain.data.PerfumesDomainDTO
import com.nane.search.presentation.data.SearchResultViewData
import javax.inject.Inject

class PerfumesMapper @Inject constructor() {
    fun toViewData(dto: PerfumesDomainDTO): List<SearchResultViewData.SearchPerfumeViewData> {
        return dto.perfumes?.map {
            SearchResultViewData.SearchPerfumeViewData(
                id = it.id,
                imgUrl = it.imgUrl,
                brandName = it.brand?.korName,
                perfumeName = it.korName
            )
        } ?: emptyList()
    }
}
