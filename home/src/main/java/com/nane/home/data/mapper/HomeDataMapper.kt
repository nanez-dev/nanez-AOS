package com.nane.home.data.mapper

import com.nane.network.api.home.HomeApi
import com.nane.home.domain.data.*
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeDataMapper @Inject constructor() {

    fun toDTO(apiData: HomeApi.Response?): HomeInfoDomainDTO {
        return HomeInfoDomainDTO(
            bannerList = apiData?.topBannerList?.map { HomeTopBannerDTO(it.image, it.link) } ?: emptyList(),
            mainTitle = HomeMainTitleDTO(
                title = apiData?.mainTitle?.title,
                content = apiData?.mainTitle?.subTitle,
            ),
            horizontalInfo = toHorizontalDTO(apiData?.horizontal),
            recommendInfo = toRecommendDTO(apiData?.mainPerfumeList),
            brandInfo = toBrandDTO(apiData?.brand),
            accordInfo = toAccordDTO(apiData?.accord)
        )
    }

    private fun toHorizontalDTO(apiData: HomeApi.Horizontal?): HomeHorizontalDTO {
        return HomeHorizontalDTO(
            title = apiData?.title,
            itemList = apiData?.itemList?.map {
                HomePerfumeDTO(imgUrl = it.image, name = it.name, brandName = it.brandName, content = it.description, volume = it.volume)
            } ?: emptyList()
        )
    }

    private fun toRecommendDTO(apiData: HomeApi.Recommend?): HomeRecommendDTO {
        return HomeRecommendDTO(
            title = apiData?.title,
            itemList = apiData?.itemList?.map {
                HomePerfumeDTO(imgUrl = it.image, name = it.name, brandName = it.brandName, content = it.description, volume = it.volume)
            } ?: emptyList()
        )
    }

    private fun toBrandDTO(apiData: HomeApi.Brand?): HomeBrandDTO {
        return HomeBrandDTO(
            title = apiData?.title,
            itemList = apiData?.itemList?.map {
                HomeBrandDTO.HomeBrandItemDTO(imgUrl = it.imgUrl, name = it.name)
            } ?: emptyList()
        )
    }

    private fun toAccordDTO(apiData: HomeApi.Accord?): HomeAccordDTO {
        return HomeAccordDTO(
            title = apiData?.title,
            itemList = apiData?.itemList?.map {
                HomeAccordDTO.HomeAccordItemDTO(imgUrl = it.imgUrl, name = it.name)
            } ?: emptyList()
        )
    }
}