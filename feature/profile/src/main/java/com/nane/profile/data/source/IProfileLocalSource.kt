package com.nane.profile.data.source

/**
 * Created by haul on 3/24/24
 */
interface IProfileLocalSource {
    suspend fun clearUserLoginInfo()
}