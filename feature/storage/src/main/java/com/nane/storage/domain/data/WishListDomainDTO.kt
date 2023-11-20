package com.nane.storage.domain.data

data class WishListDomainDTO(
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
    val brand: WishListBrandDTO?
) {
    data class WishListBrandDTO(
        val id: Int,
        val image: String?,
        val eng: String?,
        val kor: String?
    )
}