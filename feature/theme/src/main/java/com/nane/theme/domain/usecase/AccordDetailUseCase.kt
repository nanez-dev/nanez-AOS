package com.nane.theme.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.theme.domain.repository.IThemeRepository
import com.nane.theme.presentation.data.AccordDetailViewData
import com.nane.theme.presentation.mapper.AccordDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccordDetailUseCase @Inject constructor(
    private val repository: IThemeRepository,
    private val mapper: AccordDomainMapper
) {

    suspend fun getAccordDetail(id: Int): Flow<DomainResult<AccordDetailViewData>> = flow {
        repository.getAccordDetail(id = id).collect { response ->
            when (response) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(mapper.toDetailViewData(response.data)))
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