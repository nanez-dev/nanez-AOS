package com.nane.search.presentation.mapper

import com.nane.search.domain.data.PerfumesDomainDTO
import com.nane.search.presentation.data.SearchViewData
import javax.inject.Inject

class PerfumesMapper @Inject constructor() {
    fun toViewData(dto: PerfumesDomainDTO): List<SearchViewData> {
        val viewData = mutableListOf<SearchViewData>()

        viewData.add(toPerfumeViewData(dto))
        return viewData
    }

    private fun toPerfumeViewData(dto: PerfumesDomainDTO): SearchViewData.SearchPerfumeListViewType {
        return SearchViewData.SearchPerfumeListViewType(
            list = dto.perfumes?.map {
                SearchViewData.SearchPerfumeListViewType.SearchPerfumeViewData(
                    id = it.id,
                    imgUrl = it.imgUrl,
                    brandName = it.brand?.korName,
                    perfumeName = it.korName
                )
            } ?: emptyList()
        )
    }
}
