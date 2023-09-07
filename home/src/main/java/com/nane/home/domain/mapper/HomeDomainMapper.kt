package com.nane.home.domain.mapper

import com.nane.home.domain.data.*
import com.nane.home.presentation.data.HomePerfumeItemViewData
import com.nane.home.presentation.data.HomeViewData
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeDomainMapper @Inject constructor() {


    fun toViewData(dto: HomeInfoDomainDTO): List<HomeViewData> {
        val viewDataList = mutableListOf<HomeViewData>()

        if (dto.bannerList.isNotEmpty()) {
            viewDataList.add(toBannerViewData(dto.bannerList))
        }

        dto.mainTitle?.let {
            viewDataList.add(toTitleViewData(it))
        }

        dto.horizontalInfo?.let {
            viewDataList.add(toHorizontalViewData(it))
        }

        dto.recommendInfo?.let {
            viewDataList.add(toRecommendViewData(it))
        }

        dto.brandInfo?.let {
            viewDataList.add(toBrandViewData(it))
        }

        dto.accordInfo?.let {
            viewDataList.add(toAccordViewData(it))
        }

        return viewDataList.toList()
    }

    private fun toBannerViewData(dto: List<HomeTopBannerDTO>): HomeViewData.HomeBannerData {
        return HomeViewData.HomeBannerData(
            bannerList = dto.map { HomeViewData.HomeBannerData.HomeBanner(imgUrl = it.imgUrl, link = it.linkUrl) }
        )
    }

    private fun toTitleViewData(dto: HomeMainTitleDTO): HomeViewData.HomeTitleData {
        return HomeViewData.HomeTitleData(
            title = dto.title,
            content = dto.content
        )
    }

    private fun toHorizontalViewData(dto: HomeHorizontalDTO): HomeViewData.HomeHorizontalData {
        return HomeViewData.HomeHorizontalData(
            title = dto.title,
            itemList = dto.itemList.map { toPerfumeViewData(it) }
        )
    }

    private fun toRecommendViewData(dto: HomeRecommendDTO): HomeViewData.HomeRecommendPerfumeData {
        return HomeViewData.HomeRecommendPerfumeData(
            title = dto.title,
            itemList = dto.itemList.map { toPerfumeViewData(it) }
        )
    }

    private fun toBrandViewData(dto: HomeBrandDTO): HomeViewData.HomeBrandData {
        return HomeViewData.HomeBrandData(
            title = dto.title,
            itemList = dto.itemList.map {
                HomeViewData.HomeBrandData.HomeBrandItemData(
                    imgUrl = it.imgUrl,
                    brandName = it.name
                )
            }
        )
    }

    private fun toAccordViewData(dto: HomeAccordDTO): HomeViewData.HomeAccordData {
        return HomeViewData.HomeAccordData(
            title = dto.title,
            itemList = dto.itemList.map {
                HomeViewData.HomeAccordData.HomeAccordItemData(
                    imgUrl = it.imgUrl,
                    accordName = it.name
                )
            }
        )
    }


    private fun toPerfumeViewData(dto: HomePerfumeDTO): HomePerfumeItemViewData {
        return HomePerfumeItemViewData(
            imgUrl = dto.imgUrl,
            brandName = dto.brandName,
            name = dto.name,
            volume = dto.volume,
            content = dto.content
        )
    }
}