package com.nane.storage.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.network.api.storage.StorageApi
import com.nane.storage.data.repository.IStorageRepository
import com.nane.storage.domain.data.StorageDomainDTO
import javax.inject.Inject

class StorageUseCase @Inject constructor(
    private val repository: IStorageRepository
) {
    suspend fun getMyList(type: String?): DomainResult<List<StorageDomainDTO>> {
        return when (val dataResult = repository.getMyList(type)) {
            is DataResult.Success -> {
                DomainResult.Success(dataResult.data?.map { convertToWishListDomainDTO(it) } ?: emptyList())
            }
            is DataResult.Failed -> {
                DomainResult.Failed(dataResult.msg, dataResult.code)
            }
            is DataResult.Error -> {
                DomainResult.Error(dataResult.exception)
            }
        }
    }

    private fun convertToWishListDomainDTO(response: StorageApi.Response): StorageDomainDTO {
        return response.let {
            StorageDomainDTO(
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
                    StorageDomainDTO.WishListBrandDTO(
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