package org.techtown.nanez.home.data

/**
 * Created by iseungjun on 2023/08/19
 */


@Retention(AnnotationRetention.SOURCE)
annotation class HomeViewType {
    companion object {
        const val HOME_PAGER_TYPE = 1
        const val HOME_TITLE_TYPE = 2
        const val HOME_2COL_LIST_TYPE = 3
        const val HOME_3COL_LIST_TYPE = 4
    }
}