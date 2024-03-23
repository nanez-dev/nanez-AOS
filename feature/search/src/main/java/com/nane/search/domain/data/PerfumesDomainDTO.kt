package com.nane.search.domain.data

import androidx.annotation.Keep
import com.nane.theme.domain.data.BrandDTO

data class PerfumesDomainDTO(
    val perfumes: List<PerfumeDTO>?
)

data class PerfumeDTO(
    val id: Int,
    val brand: BrandDTO?,
    val density: Density?,
    val korName: String?,
    val engName: String?,
    val imgUrl: String?,
    val capacity: Int,
    val price: Int,
    val title: String?,
    val subtitle: String?,
    val isSingle: Boolean,
    val perfumeAccords: List<PerfumeAccord>?,
    val perfumeNotes: List<PerfumeNote>?,
    val perfumeTags: List<PerfumeTag>?,
    val webImageUrl1: String?,
    val webImageUrl2: String?,
    val isHaving: Boolean?,
    val isWish: Boolean?,
)

@Keep
data class Brand(
    val id: Int,
    val korName: String?,
    val engName: String?,
    val imgUrl: String?,
)

@Keep
data class Density(
    val id: Int,
    val name: String?,
)

@Keep
data class PerfumeAccord(
    val id: Int,
    val accordId: Int,
    val perfumeId: Int,
    val accord: Accord?
)

@Keep
data class Accord(
    val engName: String?,
    val korName: String?,
    val imgUrl: String?,
    val code: Int,
    val id: Int
)

@Keep
data class PerfumeNote(
    val id: Int,
    val perfumeId: Int,
    val noteId: Int,
    val type: String?,
    val note: Note?
)

@Keep
data class Note(
    val noteCategoryId: Int,
    val code: Int,
    val engName: String?,
    val korName: String?,
    val imgUrl: String?,
    val illustrationUrl: String?,
    val id: Int
)

@Keep
data class PerfumeTag(
    val id: Int,
    val perfumeId: Int,
    val tagId: Int,
    val tag: Tag?
)

@Keep
data class Tag(
    val tagCategoryId: Int,
    val code: Int,
    val name: String?,
    val id: Int
)
