package com.nane.home.domain.mapper

import com.nane.home.domain.data.HomeAccordDTO
import com.nane.home.domain.data.HomeBrandDTO
import com.nane.home.domain.data.HomeInfoDomainDTO
import com.nane.home.domain.data.HomeMainTitleDTO
import com.nane.home.domain.data.HomePerfumeDTO
import com.nane.home.domain.data.HomeRecommendPerfumeDTO
import com.nane.home.domain.data.HomeSpecialPerfumeDTO
import com.nane.home.domain.data.HomeTopBannerDTO
import com.nane.home.presentation.data.HomeViewData
import com.nane.home.presentation.data.PerfumeItemViewData
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

        dto.specialPerfumeInfo?.let {
            viewDataList.add(toSpecialPerfumeViewData(it))
        }

        dto.recommendPerfumeInfo?.let {
            viewDataList.add(toRecommendPerfumeViewData(it))
        }

        dto.brandInfo?.let {
            viewDataList.add(toBrandViewData(it))
        }

        dto.accordInfo?.let {
            viewDataList.add(toAccordViewData(it))
        }

        return viewDataList.toList()
    }

    private fun toBannerViewData(dto: List<HomeTopBannerDTO>): HomeViewData.Banner {
        return HomeViewData.Banner(
            bannerList = dto.map { HomeViewData.Banner.BannerItem(imgUrl = it.imgUrl, link = it.linkUrl) }
        )
    }

    private fun toTitleViewData(dto: HomeMainTitleDTO): HomeViewData.MainTitle {
        return HomeViewData.MainTitle(
            title = dto.title,
            content = dto.content
        )
    }

    private fun toSpecialPerfumeViewData(dto: HomeSpecialPerfumeDTO): HomeViewData.SpecialPerfume {
        return HomeViewData.SpecialPerfume(
            title = dto.title,
            itemList = dto.itemList.map { toPerfumeViewData(it) }
        )
    }

    private fun toRecommendPerfumeViewData(dto: HomeRecommendPerfumeDTO): HomeViewData.RecommondPerfume {
        return HomeViewData.RecommondPerfume(
            title = dto.title,
            itemList = dto.itemList.map { toPerfumeViewData(it) }
        )
    }

    private fun toBrandViewData(dto: HomeBrandDTO): HomeViewData.Brand {
        return HomeViewData.Brand(
            title = dto.title,
            itemList = dto.itemList.map {
                HomeViewData.Brand.BrandItem(
                    id = it.id,
                    imgUrl = it.imgUrl,
                    brandName = it.name
                )
            }
        )
    }

    private fun toAccordViewData(dto: HomeAccordDTO): HomeViewData.Accord {
        return HomeViewData.Accord(
            title = dto.title,
            itemList = dto.itemList.map {
                HomeViewData.Accord.AccordItem(
                    id = it.id,
                    imgUrl = it.imgUrl,
                    accordName = it.name
                )
            }
        )
    }


    private fun toPerfumeViewData(dto: HomePerfumeDTO): PerfumeItemViewData {
        return PerfumeItemViewData(
            imgUrl = dto.imgUrl,
            brandName = dto.brandName,
            name = dto.name,
            capacity = "${dto.capacity}ml",
            content = dto.content
        )
    }
}