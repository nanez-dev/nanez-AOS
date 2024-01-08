package com.nane.search.presentation.data

sealed class SearchViewData(@SearchViewType var viewType: Int) {
    data class RecommendationListViewType(
        val list: List<RecommendedSearchWordItemViewData> = emptyList()
    ) : SearchViewData(SearchViewType.SEARCH_RECOMMENDATIONS) {

        data class RecommendedSearchWordItemViewData(
            val word: String?
        )
    }

    data class SearchPerfumeListViewType(
        val list: List<SearchPerfumeViewData> = emptyList()
    ) : SearchViewData(SearchViewType.SEARCH_PERFUMES) {

        data class SearchPerfumeViewData(
            val id: Int,
            val imgUrl: String?,
            val brandName: String?,
            val perfumeName: String?
        )
    }

    object NoResultsViewType : SearchViewData(SearchViewType.SEARCH_NO_RESULTS)
}