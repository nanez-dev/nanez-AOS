package com.nane.search.data.repository

import com.nane.base.data.DataResult
import com.nane.network.api.search.SearchApi
import com.nane.network.parser.getParseErrorResult
import com.nane.search.data.source.ISearchRemoteSource
import com.nane.search.domain.repository.ISearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import java.io.IOException
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: ISearchRemoteSource
) : ISearchRepository {

    override suspend fun getPerfumes(
        query: String,
        loadPage: Int,
        loadSize: Int
    ): Flow<DataResult<SearchApi.Perfumes?>> = flow {
        val response = remoteDataSource.getPerfumes(query = query, loadPage = loadPage, loadSize = loadSize)
        if (response.isSuccessful) {
            emit(DataResult.Success(response.body()))
        } else {
            val failed = getParseErrorResult(response)
            emit(DataResult.Failed(failed.errorMsg, failed.errorCode))
        }
    }.retry(2) { cause ->
        cause is IOException
    }.catch { t ->
        emit(DataResult.Error(Exception(t)))
    }.flowOn(Dispatchers.IO)
}