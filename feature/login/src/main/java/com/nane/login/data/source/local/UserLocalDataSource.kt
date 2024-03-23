package com.nane.login.data.source.local

import com.nane.login.data.data.UserLoginData
import com.nane.login.data.source.IUserLocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import org.techtown.nanez.utils.NanezDataStore
import org.techtown.nanez.utils.datastore.NanezDataConst
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserLocalDataSource @Inject constructor() : IUserLocalDataSource {

    override suspend fun getUserLoginInfo(): UserLoginData {
        val email = NanezDataStore.getInstance().getValue(NanezDataConst.KEY_USER_EMAIL, NanezDataStore.ValueType.StringValue("")).firstOrNull()
        val password = NanezDataStore.getInstance().getValue(NanezDataConst.KEY_USER_PASSWORD, NanezDataStore.ValueType.StringValue("")).firstOrNull()
        val accessToken = NanezDataStore.getInstance().getValue(NanezDataConst.KEY_USER_ACCESS_TOKEN, NanezDataStore.ValueType.StringValue("")).firstOrNull()
        val refreshToken = NanezDataStore.getInstance().getValue(NanezDataConst.KEY_USER_REFRESH_TOKEN, NanezDataStore.ValueType.StringValue("")).firstOrNull()
        return UserLoginData(
            userEmail = email,
            userPassword = password,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override suspend fun saveUserLoginInfo(loginDTO: UserLoginData) {
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_EMAIL, NanezDataStore.ValueType.StringValue(loginDTO.userEmail ?: ""))
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_PASSWORD, NanezDataStore.ValueType.StringValue(loginDTO.userPassword ?: ""))
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_ACCESS_TOKEN, NanezDataStore.ValueType.StringValue(loginDTO.accessToken ?: ""))
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_REFRESH_TOKEN, NanezDataStore.ValueType.StringValue(loginDTO.refreshToken ?: ""))
    }
}