package com.nane.search.presentation.data

@Retention(AnnotationRetention.SOURCE)
annotation class SearchViewType {
    companion object {
        const val RECOMMENDATION_TYPE = 1
        const val PERFUME_TYPE = 2
    }
}