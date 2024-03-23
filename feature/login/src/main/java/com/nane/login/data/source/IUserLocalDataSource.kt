package com.nane.login.data.source

import com.nane.login.data.data.UserLoginData

/**
 * Created by haul on 3/2/24
 */
interface IUserLocalDataSource {
    suspend fun getUserLoginInfo(): UserLoginData
    suspend fun saveUserLoginInfo(loginDTO: UserLoginData)
}