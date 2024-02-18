package com.nane.theme.presentation.mapper

import com.nane.theme.domain.data.BrandsDomainDTO
import com.nane.theme.presentation.data.BrandItemViewData
import com.nane.theme.presentation.data.BrandViewData
import javax.inject.Inject

class BrandDomainMapper @Inject constructor() {

    fun toViewData(dto: BrandsDomainDTO): List<BrandViewData> {
        val viewDataList = mutableListOf<BrandViewData>()
        viewDataList.add(
            BrandViewData.PopularBrandItemListViewData(
                brandItemList = dto.popularBrands.map {
                    BrandItemViewData(
                        id = it.id,
                        korName = it.korName,
                        korDescriptionTitle = it.korDescriptionTitle,
                        korDescriptionBody = it.korDescriptionBody,
                        imgUrl = it.imgUrl,
                        relatedImgUrl = it.relatedImgUrl,
                        engName = it.engName
                    )
                }
            )
        )
        viewDataList.add(BrandViewData.BrandTitleViewData)
        viewDataList.addAll(
            dto.allBrands.map {
                BrandViewData.AllBrandItemViewData(
                    id = it.id,
                    korName = it.korName,
                    korDescriptionTitle = it.korDescriptionTitle,
                    korDescriptionBody = it.korDescriptionBody,
                    imgUrl = it.imgUrl,
                    relatedImgUrl = it.relatedImgUrl,
                    engName = it.engName
                )
            }
        )
        return viewDataList
    }
}