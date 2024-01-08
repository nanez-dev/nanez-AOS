package com.nane.search.presentation.data

@Retention(AnnotationRetention.SOURCE)
annotation class SearchViewType {
    companion object {
        const val SEARCH_RECOMMENDATIONS = 1
        const val SEARCH_PERFUMES = 2
        const val SEARCH_NO_RESULTS = 3
    }
}