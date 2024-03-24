package com.nane.network.api.search

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class SearchApi {

    @Keep
    data class Perfumes(
        val perfumes: List<Perfume>?
    )

    @Keep
    data class Perfume(
        val id: Int,
        val brand: Brand?,
        val density: Density?,
        @SerializedName("kor") val korName: String?,
        @SerializedName("eng") val engName: String?,
        @SerializedName("image") val imgUrl: String?,
        val capacity: Int,
        val price: Int,
        val title: String?,
        val subtitle: String?,
        @SerializedName("is_single") val isSingle: Boolean,
        @SerializedName("perfume_accords") val perfumeAccords: List<PerfumeAccord>?,
        @SerializedName("perfume_notes") val perfumeNotes: List<PerfumeNote>?,
        @SerializedName("perfume_tags") val perfumeTags: List<PerfumeTag>?,
        @SerializedName("web_image1") val webImageUrl1: String?,
        @SerializedName("web_image2") val webImageUrl2: String?,
        @SerializedName("is_having") val isHaving: Boolean?,
        @SerializedName("is_wish") val isWish: Boolean?,
    )

    @Keep
    data class Brand(
        val id: Int,
        @SerializedName("kor") val korName: String?,
        @SerializedName("eng") val engName: String?,
        @SerializedName("image") val imgUrl: String?,
    )

    @Keep
    data class Density(
        val id: Int,
        val name: String?,
    )

    @Keep
    data class PerfumeAccord(
        val id: Int,
        @SerializedName("accord_id") val accordId: Int,
        @SerializedName("perfume_id") val perfumeId: Int,
        val accord: Accord?
    )

    @Keep
    data class Accord(
        @SerializedName("eng") val engName: String?,
        @SerializedName("kor") val korName: String?,
        @SerializedName("image") val imgUrl: String?,
        val code: Int,
        val id: Int
    )

    @Keep
    data class PerfumeNote(
        val id: Int,
        @SerializedName("perfume_id") val perfumeId: Int,
        @SerializedName("note_id") val noteId: Int,
        val type: String?,
        val note: Note?
    )

    @Keep
    data class Note(
        @SerializedName("note_category_id") val noteCategoryId: Int,
        val code: Int,
        @SerializedName("eng") val engName: String?,
        @SerializedName("kor") val korName: String?,
        @SerializedName("image") val imgUrl: String?,
        @SerializedName("illustration") val illustrationUrl: String?,
        val id: Int
    )

    @Keep
    data class PerfumeTag(
        val id: Int,
        @SerializedName("perfume_id") val perfumeId: Int,
        @SerializedName("tag_id") val tagId: Int,
        val tag: Tag?
    )

    @Keep
    data class Tag(
        @SerializedName("tag_category_id") val tagCategoryId: Int,
        val code: Int,
        val name: String?,
        val id: Int
    )
}