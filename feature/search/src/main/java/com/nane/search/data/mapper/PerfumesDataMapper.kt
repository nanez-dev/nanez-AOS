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
                    brand = if (it.brand != null) BrandDTO(
                        id = it.brand!!.id,
                        korName = it.brand!!.korName,
                        engName = it.brand!!.engName,
                        imgUrl = it.brand!!.imgUrl,
                        engDescriptionBody = null,
                        korDescriptionBody = null,
                        relatedImgUrl = null,
                        engDescriptionTitle = null,
                        korDescriptionTitle = null
                    ) else null,
                    density = if (it.density != null) Density(
                        id = it.density!!.id,
                        name = it.density!!.name,
                    ) else null,
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
                            accord = if (perfumeAccord.accord != null) Accord(
                                engName = perfumeAccord.accord!!.engName,
                                korName = perfumeAccord.accord!!.korName,
                                imgUrl = perfumeAccord.accord!!.imgUrl,
                                code = perfumeAccord.accord!!.code,
                                id = perfumeAccord.accord!!.id
                            ) else null
                        )
                    } ?: emptyList(),
                    perfumeNotes = it.perfumeNotes?.map { perfumeNote ->
                        PerfumeNote(
                            id = perfumeNote.id,
                            perfumeId = perfumeNote.perfumeId,
                            noteId = perfumeNote.noteId,
                            type = perfumeNote.type,
                            note = if (perfumeNote.note != null) Note(
                                noteCategoryId = perfumeNote.note!!.noteCategoryId,
                                code = perfumeNote.note!!.code,
                                engName = perfumeNote.note!!.engName,
                                korName = perfumeNote.note!!.korName,
                                imgUrl = perfumeNote.note!!.imgUrl,
                                illustrationUrl = perfumeNote.note!!.illustrationUrl,
                                id = perfumeNote.note!!.id,
                            ) else null,
                        )
                    } ?: emptyList(),
                    perfumeTags = it.perfumeTags?.map { perfumeTag ->
                        PerfumeTag(
                            id = perfumeTag.id,
                            perfumeId = perfumeTag.perfumeId,
                            tagId = perfumeTag.tagId,
                            tag = if (perfumeTag.tag != null) Tag(
                                tagCategoryId = perfumeTag.tag!!.tagCategoryId,
                                code = perfumeTag.tag!!.code,
                                name = perfumeTag.tag!!.name,
                                id = perfumeTag.tag!!.id
                            ) else null
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
