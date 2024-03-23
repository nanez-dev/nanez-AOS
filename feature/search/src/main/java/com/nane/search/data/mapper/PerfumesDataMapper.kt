package com.nane.search.data.mapper

import com.nane.network.api.search.SearchApi
import com.nane.search.domain.data.Accord
import com.nane.search.domain.data.Density
import com.nane.search.domain.data.Note
import com.nane.search.domain.data.PerfumeAccord
import com.nane.search.domain.data.PerfumeDTO
import com.nane.search.domain.data.PerfumeNote
import com.nane.search.domain.data.PerfumeTag
import com.nane.search.domain.data.PerfumesDomainDTO
import com.nane.search.domain.data.Tag
import com.nane.theme.domain.data.BrandDTO
import javax.inject.Inject

class PerfumesDataMapper @Inject constructor() {
    fun toDTO(apiData: SearchApi.Perfumes?): PerfumesDomainDTO {
        return PerfumesDomainDTO(
            perfumes = apiData?.perfumes?.map {
                PerfumeDTO(
                    id = it.id,
                    brand = it.brand?.let { brand ->
                        BrandDTO(
                            id = brand.id,
                            korName = brand.korName,
                            engName = brand.engName,
                            imgUrl = brand.imgUrl,
                            engDescriptionBody = null,
                            korDescriptionBody = null,
                            relatedImgUrl = null,
                            engDescriptionTitle = null,
                            korDescriptionTitle = null
                        )
                    },
                    density = it.density?.let { density ->
                        Density(
                            id = density.id,
                            name = density.name,
                        )
                    },
                    korName = it.korName,
                    engName = it.engName,
                    imgUrl = it.imgUrl,
                    capacity = it.capacity,
                    price = it.price,
                    title = it.title,
                    subtitle = it.subtitle,
                    isSingle = it.isSingle,
                    perfumeAccords = it.perfumeAccords?.map { perfumeAccord ->
                        PerfumeAccord(
                            id = perfumeAccord.id,
                            accordId = perfumeAccord.accordId,
                            perfumeId = perfumeAccord.perfumeId,
                            accord = perfumeAccord.accord?.let { accord ->
                                Accord(
                                    engName = accord.engName,
                                    korName = accord.korName,
                                    imgUrl = accord.imgUrl,
                                    code = accord.code,
                                    id = accord.id
                                )
                            }
                        )
                    } ?: emptyList(),
                    perfumeNotes = it.perfumeNotes?.map { perfumeNote ->
                        PerfumeNote(
                            id = perfumeNote.id,
                            perfumeId = perfumeNote.perfumeId,
                            noteId = perfumeNote.noteId,
                            type = perfumeNote.type,
                            note = perfumeNote.note?.let { note ->
                                Note(
                                    noteCategoryId = note.noteCategoryId,
                                    code = note.code,
                                    engName = note.engName,
                                    korName = note.korName,
                                    imgUrl = note.imgUrl,
                                    illustrationUrl = note.illustrationUrl,
                                    id = note.id,
                                )
                             },
                        )
                    } ?: emptyList(),
                    perfumeTags = it.perfumeTags?.map { perfumeTag ->
                        PerfumeTag(
                            id = perfumeTag.id,
                            perfumeId = perfumeTag.perfumeId,
                            tagId = perfumeTag.tagId,
                            tag = perfumeTag.tag?.let { tag ->
                                Tag(
                                    tagCategoryId = tag.tagCategoryId,
                                    code = tag.code,
                                    name = tag.name,
                                    id = tag.id
                                )
                            }
                        )
                    } ?: emptyList(),
                    webImageUrl1 = it.webImageUrl1,
                    webImageUrl2 = it.webImageUrl2,
                    isHaving = it.isHaving,
                    isWish = it.isWish
                )
            } ?: emptyList()
        )
    }
}
