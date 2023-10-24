package com.nane.home.presentation.data

/**
 * Created by iseungjun on 2023/08/19
 */


@Retention(AnnotationRetention.SOURCE)
annotation class HomeViewType {
    companion object {
        const val HOME_PAGER_TYPE = 1
        const val HOME_TITLE_TYPE = 2
        const val HOME_HORI_LIST_TYPE = 3
        const val HOME_RECOMMEND_TYPE = 4
        const val HOME_BRAND_TYPE = 5
        const val HOME_ACCORD_TYPE = 6
    }
}