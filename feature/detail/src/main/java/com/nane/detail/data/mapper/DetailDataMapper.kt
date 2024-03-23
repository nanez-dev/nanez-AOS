package com.nane.detail.data.mapper

import com.nane.detail.domain.data.PerfumeDetailDTO
import com.nane.network.api.detail.PerfumeDetailApi
import javax.inject.Inject

/**
 * Created by haul on 3/10/24
 */
class DetailDataMapper @Inject constructor() {

    fun toDto(data: PerfumeDetailApi.Perfume?): PerfumeDetailDTO {
        return PerfumeDetailDTO(
            id = data?.id ?: -1,
            korName = data?.kor,
            engName = data?.eng,
            imageUrl = data?.image,
            price = data?.price ?: -1,
            capacity = data?.capacity ?: -1,
            isHaving = data?.is_having ?: false,
            isWish = data?.is_wish ?: false,
            accord = data?.perfume_accords?.map { toAccordDto(it) },
            brand = data?.brand?.let { toBrandDto(it) },
            tag = data?.perfume_tags?.map { toTagDto(it) },
            note = data?.perfume_notes?.let { toNoteDto(it, data.title, data.subtitle) },
        )
    }

    private fun toAccordDto(data: PerfumeDetailApi.Accord): PerfumeDetailDTO.AccordDTO {
        return PerfumeDetailDTO.AccordDTO(
            id = data.accord_id,
            engName = data.accord?.eng,
            korName = data.accord?.kor,
            imageUrl = data.accord?.image
        )
    }

    private fun toBrandDto(data: PerfumeDetailApi.Brand): PerfumeDetailDTO.BrandDTO {
        return PerfumeDetailDTO.BrandDTO(
            id = data.id,
            engName = data.eng,
            korName = data.kor,
            imageUrl = data.image
        )
    }

    private fun toTagDto(data: PerfumeDetailApi.Tag): PerfumeDetailDTO.TagDTO {
        return PerfumeDetailDTO.TagDTO(
            id = data.tag_id,
            categoryId = data.tag?.tag_category_id ?: -1,
            name = data.tag?.name
        )
    }

    private fun toNoteDto(data: List<PerfumeDetailApi.Note>, title: String?, subTitle: String?): PerfumeDetailDTO.NoteDTO {
        return PerfumeDetailDTO.NoteDTO(
            title = title,
            subTitle = subTitle,
            detail = data.map {
                PerfumeDetailDTO.NoteDTO.DetailDTO(
                    id = it.note_id,
                    type = it.type,
                    engName = it.note?.eng,
                    korName = it.note?.kor,
                    illustration = it.note?.illustration
                )
            }
        )
    }
}