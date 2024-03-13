package com.nane.detail.domain.mapper

import com.nane.detail.domain.data.PerfumeDetailDTO
import com.nane.detail.presentation.data.PerfumeDetailViewData
import javax.inject.Inject

/**
 * Created by haul on 3/10/24
 */
class DetailDomainMapper @Inject constructor() {

    fun toViewData(data: PerfumeDetailDTO): PerfumeDetailViewData {
        return PerfumeDetailViewData(
            basicInfo = PerfumeDetailViewData.Basic(
                id = data.id,
                korName = data.korName,
                engName = data.engName,
                brandName = data.brand?.korName,
                price = data.price,
                capacity = data.capacity.toString(),
                imageUrl = data.imageUrl,
                isWish = data.isWish,
                isHaving = data.isHaving
            ),
            accordInfo = data.accord?.map { toAccordViewData(it) },
            noteInfo = data.note?.let { toNoteViewData(data.note) }
        )
    }


    private fun toAccordViewData(data: PerfumeDetailDTO.AccordDTO): PerfumeDetailViewData.Accord {
        return PerfumeDetailViewData.Accord(
            id = data.id,
            engName = data.engName,
            imageUrl = data.imageUrl
        )
    }


    private fun toNoteViewData(data: PerfumeDetailDTO.NoteDTO): PerfumeDetailViewData.Note {
        return PerfumeDetailViewData.Note(
            title = data.title,
            subTitle = data.subTitle,
            allTopNoteStr = data.detail?.filter { it.type == "T" && it.engName?.isNotEmpty() == true }?.map { it.engName }?.reduce { acc, s -> "$acc, $s" },
            allMiddleNoteStr = data.detail?.filter { it.type == "M" && it.engName?.isNotEmpty() == true }?.map { it.engName }?.reduce { acc, s -> "$acc, $s" },
            allBaseNoteStr = data.detail?.filter { it.type == "B" && it.engName?.isNotEmpty() == true }?.map { it.engName }?.reduce { acc, s -> "$acc, $s" },
            topNote = data.detail?.firstOrNull { it.type == "T" }?.let { toNoteDetailViewData(it) },
            middleNote = data.detail?.firstOrNull { it.type == "M" }?.let { toNoteDetailViewData(it) },
            baseNote = data.detail?.firstOrNull { it.type == "B" }?.let { toNoteDetailViewData(it) },
        )
    }

    private fun toNoteDetailViewData(data: PerfumeDetailDTO.NoteDTO.DetailDTO): PerfumeDetailViewData.Note.Detail {
        return PerfumeDetailViewData.Note.Detail(
            name = data.engName,
            imageUrl = data.illustration
        )
    }
}