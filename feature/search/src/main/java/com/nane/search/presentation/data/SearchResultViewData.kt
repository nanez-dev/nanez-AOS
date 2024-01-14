package com.nane.search.presentation.data

sealed class SearchResultViewData(@SearchViewType var viewType: Int) {
    data class RecommendedSearchWordListItemViewData(
        var wordList: List<String>
    ): SearchResultViewData(SearchViewType.RECOMMENDATION_TYPE)

    data class SearchPerfumeViewData(
        val id: Int,
        val imgUrl: String?,
        val brandName: String?,
        val perfumeName: String?
    ) : SearchResultViewData(SearchViewType.PERFUME_TYPE)
}