package com.nane.detail.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.detail.data.repo.IDetailRepository
import com.nane.detail.domain.mapper.DetailDomainMapper
import com.nane.detail.presentation.data.PerfumeDetailViewData
import javax.inject.Inject

/**
 * Created by haul on 3/10/24
 */
class PerfumeDetailUseCase @Inject constructor(
    private val repository: IDetailRepository,
    private val mapper: DetailDomainMapper,
) {

    suspend fun getPerfumeDetail(targetId: Int): DomainResult<PerfumeDetailViewData> {
        return when (val result = repository.getPerfumeDetail(targetId)) {
            is DataResult.Success -> {
                DomainResult.Success(mapper.toViewData(result.data))
            }
            is DataResult.Failed -> {
                DomainResult.Failed(result.msg, result.code)
            }
            is DataResult.Error -> {
                DomainResult.Error(result.exception)
            }
        }
    }

}