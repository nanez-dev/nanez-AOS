package com.nane.theme.presentation.mapper

import com.nane.theme.domain.data.BrandDetailDomainDTO
import com.nane.theme.domain.data.BrandsDomainDTO
import com.nane.theme.presentation.data.BrandDetailViewData
import com.nane.theme.presentation.data.BrandItemViewData
import com.nane.theme.presentation.data.BrandViewData
import com.nane.theme.presentation.data.PerfumeViewData
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

    fun toDetailViewData(dto: BrandDetailDomainDTO): BrandDetailViewData {
        return BrandDetailViewData(
            brandItemViewData = if (dto.brand != null) BrandItemViewData(
                id = dto.brand.id,
                korName = dto.brand.korName,
                engDescriptionBody = dto.brand.engDescriptionBody,
                korDescriptionBody = dto.brand.korDescriptionBody,
                relatedImgUrl = dto.brand.relatedImgUrl,
                engName = dto.brand.engName,
                engDescriptionTitle = dto.brand.engDescriptionTitle,
                korDescriptionTitle = dto.brand.korDescriptionTitle,
                imgUrl = dto.brand.imgUrl
            ) else null,
            relatedPerfumes = dto.relatedPerfumes?.map {
                PerfumeViewData(
                    id = it.id,
                    korName = it.korName,
                    engName = it.engName,
                    imgUrl = it.imgUrl,
                    capacity = it.capacity,
                    price = it.price,
                    brand = if (it.brand != null) BrandItemViewData(
                        id = it.brand.id,
                        korName = it.brand.korName,
                        engDescriptionBody = it.brand.engDescriptionBody,
                        korDescriptionBody = it.brand.korDescriptionBody,
                        relatedImgUrl = it.brand.relatedImgUrl,
                        engName = it.brand.engName,
                        engDescriptionTitle = it.brand.engDescriptionTitle,
                        korDescriptionTitle = it.brand.korDescriptionTitle,
                        imgUrl = it.brand.imgUrl
                    ) else null,
                    brandId = it.brandId,
                    isSingle = it.isSingle,
                    webImgUrl1 = it.webImgUrl1,
                    webImgUrl2 = it.webImgUrl2,
                    densityId = it.densityId,
                    title = it.title,
                    rating = it.rating,
                    description = it.description
                )
            } ?: emptyList()
        )
    }
}