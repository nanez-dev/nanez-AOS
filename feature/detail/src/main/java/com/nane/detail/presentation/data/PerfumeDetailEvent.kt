package com.nane.detail.presentation.data

/**
 * Created by haul on 3/10/24
 */
sealed class PerfumeDetailEvent {

    data class InitView(val viewData: PerfumeDetailViewData) : PerfumeDetailEvent()
    data class RefreshWish(val isWish: Boolean) : PerfumeDetailEvent()
    data class RefreshHaving(val isHaving: Boolean) : PerfumeDetailEvent()

    object Finish : PerfumeDetailEvent()

}