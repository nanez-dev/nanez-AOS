package org.techtown.nanez.home.data

/**
 * Created by iseungjun on 2023/08/19
 */

sealed class HomeViewData(@HomeViewType var viewType: Int) {

    data class HomeBannerData(
        val bannerList: List<String>? = emptyList()
    ) : HomeViewData(HomeViewType.HOME_PAGER_TYPE)
}
