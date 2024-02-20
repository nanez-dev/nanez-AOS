package com.nane.search.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.search.data.mapper.PerfumesDataMapper
import com.nane.search.domain.data.PerfumesDomainDTO
import com.nane.search.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PerfumesUsecase @Inject constructor(
    private val repository: ISearchRepository,
    private val mapper: PerfumesDataMapper
) {
    suspend fun getPerfumes(query: String, loadPage: Int, loadSize: Int): Flow<DomainResult<PerfumesDomainDTO>> = flow {
        repository.getPerfumes(query, loadPage, loadSize).collect { response ->
            when (response) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(mapper.toDTO(response.data)))
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(response.msg, response.code))
                }
                is DataResult.Error -> {
                    emit(DomainResult.Error(response.exception))
                }
            }
        }
    }
}