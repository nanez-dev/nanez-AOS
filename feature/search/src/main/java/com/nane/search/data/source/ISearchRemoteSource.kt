package com.nane.search.data.source

import com.nane.network.api.search.SearchApi
import retrofit2.Response

interface ISearchRemoteSource {
    suspend fun getPerfumes(query: String, loadPage: Int, loadSize: Int): Response<SearchApi.Perfumes>
}