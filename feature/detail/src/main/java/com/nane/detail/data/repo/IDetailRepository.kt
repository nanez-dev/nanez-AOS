package com.nane.detail.data.repo

import com.nane.base.data.DataResult
import com.nane.detail.domain.data.PerfumeDetailDTO

/**
 * Created by haul on 3/10/24
 */
interface IDetailRepository {
    suspend fun getPerfumeDetail(targetId: Int): DataResult<PerfumeDetailDTO>
    suspend fun patchPerfumeWish(perfumeId: Int): DataResult<Boolean>
    suspend fun patchPerfumeHaving(perfumeId: Int): DataResult<Boolean>
}