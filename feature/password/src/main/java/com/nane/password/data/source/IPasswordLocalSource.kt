package com.nane.password.data.source

/**
 * Created by haul on 3/24/24
 */
interface IPasswordLocalSource {
    suspend fun clearUserLoginInfo()
}