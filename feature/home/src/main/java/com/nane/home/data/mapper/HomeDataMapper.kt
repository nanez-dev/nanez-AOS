package com.nane.home.data.mapper

import com.nane.home.domain.data.*
import com.nane.network.api.home.HomeApi
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeDataMapper @Inject constructor() {

    fun toDTO(apiData: HomeApi.Response?): HomeInfoDomainDTO {
        return HomeInfoDomainDTO(
            bannerList = apiData?.topBannerList?.list?.map { HomeTopBannerDTO(it.image, it.link) } ?: emptyList(),
            mainTitle = HomeMainTitleDTO(
                title = apiData?.topBannerList?.title,
                content = apiData?.topBannerList?.subTitle,
            ),
            specialPerfumeInfo = toSpecialDTO(apiData?.specialPerfumeList),
            recommendPerfumeInfo = toRecommendDTO(apiData?.recommendPerfumeList),
            brandInfo = toBrandDTO(apiData?.popularBrandList),
            accordInfo = toAccordDTO(apiData?.recommendAccordList)
        )
    }

    private fun toSpecialDTO(apiData: HomeApi.SpecialPerfume?): HomeSpecialPerfumeDTO {
        return HomeSpecialPerfumeDTO(
            title = apiData?.title,
            itemList = apiData?.list?.map {
                HomePerfumeDTO(imgUrl = it.image, name = it.name, brandName = it.brand?.name, content = it.description, capacity = it.capacity)
            } ?: emptyList()
        )
    }

    private fun toRecommendDTO(apiData: HomeApi.RecommendPerfume?): HomeRecommendPerfumeDTO {
        return HomeRecommendPerfumeDTO(
            title = apiData?.title,
            itemList = apiData?.list?.map {
                HomePerfumeDTO(imgUrl = it.image, name = it.name, brandName = it.brand?.name, content = it.description, capacity = it.capacity)
            } ?: emptyList()
        )
    }

    private fun toBrandDTO(apiData: HomeApi.PopularBrand?): HomeBrandDTO {
        return HomeBrandDTO(
            title = apiData?.title,
            itemList = apiData?.list?.map {
                HomeBrandDTO.HomeBrandItemDTO(id = it.id, imgUrl = it.image, name = it.name)
            } ?: emptyList()
        )
    }

    private fun toAccordDTO(apiData: HomeApi.RecommendAccord?): HomeAccordDTO {
        return HomeAccordDTO(
            title = apiData?.title,
            itemList = apiData?.list?.map {
                HomeAccordDTO.HomeAccordItemDTO(id = it.id, imgUrl = it.image, name = it.name)
            } ?: emptyList()
        )
    }
}