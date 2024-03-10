package com.nane.detail.domain.data

/**
 * Created by haul on 3/10/24
 */
data class PerfumeDetailDTO(
    val id: Int,
    val korName: String?,
    val engName: String?,
    val price: Int,
    val imageUrl: String?,
    val capacity: Int,
    val isWish: Boolean,
    val isHaving: Boolean,
    val brand: BrandDTO?,
    val accord: List<AccordDTO>?,
    val note: NoteDTO?,
    val tag: List<TagDTO>?
) {

    data class BrandDTO(
        val id: Int,
        val engName: String?,
        val korName: String?,
        val imageUrl: String?,
    )

    data class AccordDTO(
        val id: Int,
        val engName: String?,
        val korName: String?,
        val imageUrl: String?,
    )

    data class NoteDTO(
        val title: String?,
        val subTitle: String?,
        val detail: List<DetailDTO>?
    ) {
        data class DetailDTO(
            val id: Int,
            val type: String?,
            val engName: String?,
            val korName: String?,
            val illustration: String?,
        )
    }

    data class TagDTO(
        val id: Int,
        val categoryId: Int,
        val name: String?,
    )
}