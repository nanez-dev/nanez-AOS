package com.nane.theme.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.theme.data.mapper.AccordDetailDataMapper
import com.nane.theme.domain.data.AccordDetailDomainDTO
import com.nane.theme.domain.repository.IThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccordDetailUsecase @Inject constructor(
    private val repository: IThemeRepository,
    private val mapper: AccordDetailDataMapper
) {

    suspend fun getAccordDetail(id: Int): Flow<DomainResult<AccordDetailDomainDTO>> = flow {
        repository.getAccordDetail(id = id).collect { response ->
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