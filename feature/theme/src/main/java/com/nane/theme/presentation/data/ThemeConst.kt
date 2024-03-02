package com.nane.theme.presentation.data

/**
 * Created by haul on 3/2/24
 */

@Retention(AnnotationRetention.SOURCE)
annotation class AccordViewType {
    companion object {
        const val POPULAR_ITEM_LIST_TYPE = 1
        const val TITLE_TYPE = 2
        const val ALL_ACCORD_ITEM_TYPE = 3
    }
}

@Retention(AnnotationRetention.SOURCE)
annotation class BrandViewType {
    companion object {
        const val POPULAR_ITEM_LIST_TYPE = 1
        const val TITLE_TYPE = 2
        const val ALL_BRAND_ITEM_TYPE = 3
    }
}