package com.nane.storage.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.network.api.storage.StorageApi
import com.nane.storage.data.repository.IWishListRepository
import com.nane.storage.domain.data.WishListDomainDTO
import javax.inject.Inject

class WishListUseCase @Inject constructor(
    private val repository: IWishListRepository
) {
    suspend fun getMyList(type: String?): DomainResult<WishListDomainDTO> {
        val dataResult = repository.getMyList(type)
        return when (dataResult) {
            is DataResult.Success -> {
                val wishListDomainDTO = convertToWishListDomainDTO(dataResult.data)
                DomainResult.Success(wishListDomainDTO!!)
            }
            is DataResult.Failed -> {
                DomainResult.Failed(dataResult.msg, dataResult.code)
            }
            is DataResult.Error -> {
                DomainResult.Error(dataResult.exception)
            }
        }
    }

    private fun convertToWishListDomainDTO(response: StorageApi.Response?): WishListDomainDTO? {
        return response?.let {
            WishListDomainDTO(
                kor = it.kor,
                isSingle = it.is_single,
                image = it.image,
                webImage1 = it.web_image1,
                brandId = it.brand_id,
                webImage2 = it.web_image2,
                id = it.id,
                capacity = it.capacity,
                densityId = it.density_id,
                price = it.price,
                eng = it.eng,
                title = it.title,
                subtitle = it.subtitle,
                brand = it.brand?.let { brand ->
                    WishListDomainDTO.WishListBrandDTO(
                        id = brand.id,
                        image = brand.image,
                        eng = brand.eng,
                        kor = brand.kor
                    )
                }
            )
        }
    }
}