package com.nane.network.api.storage

import androidx.annotation.Keep

@Keep
class StorageApi {

    @Keep
    data class Response(
        val kor: String?,
        val is_single: Boolean,
        val image: String?,
        val web_image1: String?,
        val brand_id: Int,
        val web_image2: String?,
        val id: Int,
        val capacity: Int,
        val density_id: Int,
        val price: Int,
        val eng: String?,
        val title: String?,
        val subtitle: String?,
        val brand: Brand?
    )

    @Keep
    data class Brand(
        val id: Int,
        val image: String?,
        val eng: String?,
        val kor: String?
    )
}