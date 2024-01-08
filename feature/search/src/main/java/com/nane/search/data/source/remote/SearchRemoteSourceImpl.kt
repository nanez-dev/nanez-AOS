package com.nane.search.data.source.remote

import com.nane.network.api.search.SearchApi
import com.nane.network.service.RetrofitSearchService
import com.nane.search.data.source.ISearchRemoteSource
import retrofit2.Response
import javax.inject.Inject

class SearchRemoteSourceImpl @Inject constructor(
    private val searchService: RetrofitSearchService
): ISearchRemoteSource {
    override suspend fun getPerfumes(
        query: String,
        loadPosition: Int,
        loadSize: Int
    ): Response<SearchApi.Perfumes> {
        return searchService.getPerfumes(query = query, loadPosition = loadPosition, loadSize = loadSize)
    }
}