package org.techtown.nanez.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.techtown.nanez.data.data.DataResult
import org.techtown.nanez.data.repository.HomeRepository
import org.techtown.nanez.domain.data.DomainResult
import org.techtown.nanez.domain.data.HomeInfoDomainDTO
import org.techtown.nanez.domain.mapper.HomeDomainMapper
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/26
 */
class HomeInfoUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val mapper: HomeDomainMapper,
) {

    suspend fun getHomeInfo(): Flow<DomainResult<HomeInfoDomainDTO>> = flow {
        repository.getHomeInfo().collect { response ->
            when (response) {
                is DataResult.Success -> {
                    emit(DomainResult.Success(mapper.toDTO(response.data)))
                }
                is DataResult.Failed -> {
                    emit(DomainResult.Failed(response.msg))
                }
                is DataResult.Error -> {
                    emit(DomainResult.Error(response.exception))
                }
            }
        }
    }
}