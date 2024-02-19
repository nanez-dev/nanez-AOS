package com.nane.storage.presentation.data

import com.nane.storage.domain.data.StorageDomainDTO

sealed class StorageViewData {

    data class StorageItem(
        val kor: String?,
        val isSingle: Boolean,
        val image: String?,
        val webImage1: String?,
        val brandId: Int,
        val webImage2: String?,
        val id: Int,
        val capacity: Int,
        val densityId: Int,
        val price: Int,
        val eng: String?,
        val title: String?,
        val subtitle: String?,
        val brand: BrandItem?
    ) : StorageViewData() {

        data class BrandItem(
            val id: Int,
            val image: String?,
            val eng: String?,
            val kor: String?
        ) {
            companion object {
                fun fromApiModel(apiResponse: StorageDomainDTO.WishListBrandDTO): BrandItem {
                    return BrandItem(
                        apiResponse.id,
                        apiResponse.image,
                        apiResponse.eng,
                        apiResponse.kor
                    )
                }
            }
        }

        companion object {
            fun fromApiModel(apiResponse: StorageDomainDTO): StorageItem {
                return StorageItem(
                    apiResponse.kor,
                    apiResponse.isSingle,
                    apiResponse.image,
                    apiResponse.webImage1,
                    apiResponse.brandId,
                    apiResponse.webImage2,
                    apiResponse.id,
                    apiResponse.capacity,
                    apiResponse.densityId,
                    apiResponse.price,
                    apiResponse.eng,
                    apiResponse.title,
                    apiResponse.subtitle,
                    apiResponse.brand?.let { BrandItem.fromApiModel(it) }
                )
            }
        }
    }
}