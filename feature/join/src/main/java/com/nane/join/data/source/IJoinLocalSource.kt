package com.nane.join.data.source

/**
 * Created by haul on 11/30/23
 */
interface IJoinLocalSource {
    suspend fun saveUserToken(accessToken: String?, refreshToken: String?)
}