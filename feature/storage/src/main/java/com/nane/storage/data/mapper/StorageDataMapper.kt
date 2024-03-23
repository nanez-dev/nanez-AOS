package com.nane.storage.data.mapper

import com.nane.network.api.storage.StorageApi
import com.nane.storage.domain.data.StorageDomainDTO
import javax.inject.Inject

/**
 * Created by haul on 3/2/24
 */
class StorageDataMapper @Inject constructor() {

    fun toDTO(response: StorageApi.Response): StorageDomainDTO {
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