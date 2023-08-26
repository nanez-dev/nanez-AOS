package org.techtown.nanez.domain.mapper

import org.techtown.nanez.data.api.home.HomeApi
import org.techtown.nanez.domain.data.*
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeDomainMapper @Inject constructor() {


    fun toDTO(apiData: HomeApi.Response?): HomeInfoDomainDTO {
        return HomeInfoDomainDTO(
            bannerList = apiData?.imgList ?: emptyList(),
            mainTitle = HomeMainTitleDTO(
                title = apiData?.mainTitle?.title,
                content = apiData?.mainTitle?.subTitle,
            ),
            horizontalInfo = toHorizontalDTO(apiData?.horizontal),
            recommendInfo = toRecommendDTO(apiData?.recommend),
            brandInfo = toBrandDTO(apiData?.brand),
            accordInfo = toAccordDTO(apiData?.accord)
        )
    }

    private fun toHorizontalDTO(apiData: HomeApi.Horizontal?): HomeHorizontalDTO {
        return HomeHorizontalDTO(
            title = apiData?.title,
            itemList = apiData?.itemList?.map {
                HomePerfumeDTO(imgUrl = it.imgUrl, name = it.name, brandName = it.brandName, content = it.description, volume = it.volume)
            } ?: emptyList()
        )
    }

    private fun toRecommendDTO(apiData: HomeApi.Recommend?): HomeRecommendDTO {
        return HomeRecommendDTO(
            title = apiData?.title,
            itemList = apiData?.itemList?.map {
                HomePerfumeDTO(imgUrl = it.imgUrl, name = it.name, brandName = it.brandName, content = it.description, volume = it.volume)
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