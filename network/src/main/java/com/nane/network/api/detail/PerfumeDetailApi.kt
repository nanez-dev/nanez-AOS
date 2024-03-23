package com.nane.network.api.detail

import androidx.annotation.Keep

/**
 * Created by haul on 3/10/24
 */
@Keep
class PerfumeDetailApi {

    @Keep
    data class Response(
        val perfume: Perfume?
    )

    @Keep
    data class Perfume(
        val id: Int,
        val kor: String?,
        val eng: String?,
        val image: String?,
        val capacity: Int,
        val price: Int,
        val title: String?,
        val subtitle: String?,
        val is_single: Boolean,
        val web_image1: String?,
        val web_image2: String?,
        val is_having: Boolean,
        val is_wish: Boolean,
        val brand: Brand?,
        val density: Density?,
        val perfume_accords: List<Accord>?,
        val perfume_notes: List<Note>?,
        val perfume_tags: List<Tag>?,
    )

    @Keep
    data class Brand(
        val id: Int,
        val eng: String?,
        val kor: String?,
        val image: String?,
    )

    @Keep
    data class Density(
        val id: Int,
        val name: String?,
    )

    @Keep
    data class Accord(
        val id: Int,
        val accord_id: Int,
        val perfume_id: Int,
        val accord: Detail?,
    ) {
        @Keep
        data class Detail(
            val eng: String?,
            val kor: String?,
            val image: String?,
            val code: Int,
            val id: Int,
        )
    }

    @Keep
    data class Note(
        val id: Int,
        val perfume_id: Int,
        val note_id: Int,
        val type: String?,
        val note: Detail?,
    ) {
        @Keep
        data class Detail(
            val note_category_id: Int,
            val eng: String?,
            val kor: String?,
            val image: String?,
            val code: Int,
            val illustration: String?,
            val id: Int,
        )
    }

    @Keep
    data class Tag(
        val id: Int,
        val perfume_id: Int,
        val tag_id: Int,
        val tag: Detail?,
    ) {
        @Keep
        data class Detail(
            val tag_category_id: Int,
            val code: Int,
            val name: String?,
            val id: Int,
        )
    }
}