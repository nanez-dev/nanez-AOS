package com.nane.storage.data.source

import retrofit2.Response

interface IWishListRemoteSource {
    // btn 대신 직관적인 type이라는 변수 명을 사용
    // API를 그대로 반환하는 역할
    // 코루틴 때문에 suspend는 붙여야 한다
    // RetrofitPerfumeService와 동일한 응답을 받아야 함
    suspend fun getMyList(type: String?): Response<String>
}