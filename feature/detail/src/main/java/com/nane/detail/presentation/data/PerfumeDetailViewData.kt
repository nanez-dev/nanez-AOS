package com.nane.detail.presentation.data

/**
 * Created by haul on 3/10/24
 */
data class PerfumeDetailViewData(
    val basicInfo: Basic?,
    val accordInfo: List<Accord>?,
    val noteInfo: Note?,
) {

    data class Basic(
        val id: Int,
        val korName: String?,
        val engName: String?,
        val brandName: String?,
        val price: Int,
        val imageUrl: String?,
        val capacity: String?,
        val isWish: Boolean,
        val isHaving: Boolean,
    )

    data class Accord(
        val id: Int,
        val engName: String?,
        val imageUrl: String?,
    )

    data class Note(
        val title: String?,
        val subTitle: String?,
        val allTopNoteStr: String?,
        val allMiddleNoteStr: String?,
        val allBaseNoteStr: String?,
        val topNote: Detail?,
        val middleNote: Detail?,
        val baseNote: Detail?,
    ) {
        data class Detail(
            val name: String?,
            val imageUrl: String?,
        )
    }
}





