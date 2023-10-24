package com.nane.home.domain.usecase

import com.nane.base.data.DataResult
import com.nane.base.data.DomainResult
import com.nane.home.data.mapper.HomeDataMapper
import com.nane.home.domain.data.HomeInfoDomainDTO
import com.nane.home.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeInfoUseCase @Inject constructor(
    private val repository: IHomeRepository,
    private val mapper: HomeDataMapper,
) {

    suspend fun getHomeInfo(): Flow<DomainResult<HomeInfoDomainDTO>> = flow {
        repository.getHomeInfo().collect { response ->
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