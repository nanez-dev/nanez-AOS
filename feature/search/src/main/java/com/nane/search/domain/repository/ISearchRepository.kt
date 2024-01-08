package com.nane.search.domain.repository

import com.nane.base.data.DataResult
import com.nane.network.api.search.SearchApi
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    suspend fun getPerfumes(query: String, loadPosition: Int, loadSize: Int): Flow<DataResult<SearchApi.Perfumes?>>
}