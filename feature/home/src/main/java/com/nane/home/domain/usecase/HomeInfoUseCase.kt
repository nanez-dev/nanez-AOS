package com.nane.home.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.home.domain.mapper.HomeDomainMapper
import com.nane.home.domain.repository.IHomeRepository
import com.nane.home.presentation.data.HomeViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeInfoUseCase @Inject constructor(
    private val repository: IHomeRepository,
    private val mapper: HomeDomainMapper,
) {

    suspend fun getHomeInfo(): Flow<DomainResult<List<HomeViewData>>> = flow {
        repository.getHomeInfo().collect { response ->
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