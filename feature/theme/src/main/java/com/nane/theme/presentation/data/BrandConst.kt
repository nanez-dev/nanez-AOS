package com.nane.theme.presentation.data

@Retention(AnnotationRetention.SOURCE)
annotation class BrandViewType {
    companion object {
        const val POPULAR_ITEM_LIST_TYPE = 1
        const val TITLE_TYPE = 2
        const val ALL_BRAND_ITEM_TYPE = 3
    }
}