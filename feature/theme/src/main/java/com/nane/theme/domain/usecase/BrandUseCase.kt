package com.nane.theme.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.theme.domain.repository.IThemeRepository
import com.nane.theme.presentation.data.BrandViewData
import com.nane.theme.presentation.mapper.BrandDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BrandUseCase @Inject constructor(
    private val repository: IThemeRepository,
    private val mapper: BrandDomainMapper
) {

    suspend fun getAllBrands(): Flow<DomainResult<List<BrandViewData>>> = flow {
        repository.getBrands().collect { response ->
            when (response) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(mapper.toViewData(response.data)))
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