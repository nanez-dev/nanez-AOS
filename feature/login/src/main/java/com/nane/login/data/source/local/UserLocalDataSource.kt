package com.nane.login.data.source.local

import com.nane.login.data.data.UserLoginDTO
import kotlinx.coroutines.flow.firstOrNull
import org.techtown.nanez.utils.NanezDataStore
import org.techtown.nanez.utils.datastore.NanezDataConst
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserLocalDataSource @Inject constructor() {

    suspend fun getUserLoginInfo(): UserLoginDTO {
        val email = NanezDataStore.getInstance().getValue(NanezDataConst.KEY_USER_EMAIL, NanezDataStore.ValueType.StringValue("")).firstOrNull()
        val password = NanezDataStore.getInstance().getValue(NanezDataConst.KEY_USER_PASSWORD, NanezDataStore.ValueType.StringValue("")).firstOrNull()
        val accessToken = NanezDataStore.getInstance().getValue(NanezDataConst.KEY_USER_ACCESS_TOKEN, NanezDataStore.ValueType.StringValue("")).firstOrNull()
        val refreshToken = NanezDataStore.getInstance().getValue(NanezDataConst.KEY_USER_REFRESH_TOKEN, NanezDataStore.ValueType.StringValue("")).firstOrNull()
        return UserLoginDTO(
            userEmail = email,
            userPassword = password,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    suspend fun saveUserLoginInfo(loginDTO: UserLoginDTO) {
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_EMAIL, NanezDataStore.ValueType.StringValue(loginDTO.userEmail ?: ""))
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_PASSWORD, NanezDataStore.ValueType.StringValue(loginDTO.userPassword ?: ""))
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_ACCESS_TOKEN, NanezDataStore.ValueType.StringValue(loginDTO.accessToken ?: ""))
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_REFRESH_TOKEN, NanezDataStore.ValueType.StringValue(loginDTO.refreshToken ?: ""))
    }
}