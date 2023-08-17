package org.techtown.nanez.data.source.local

import kotlinx.coroutines.flow.firstOrNull
import org.techtown.nanez.data.data.UserLoginDTO
import org.techtown.nanez.utils.NanezDataStore
import org.techtown.nanez.utils.datastore.NanezDataConst
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserLocalDataSource @Inject constructor(){

    suspend fun getUserLoginInfo(): UserLoginDTO {
        val email = NanezDataStore.getInstance().getValue(NanezDataConst.KEY_USER_EMAIL, NanezDataStore.ValueType.StringValue("")).firstOrNull()
        val password = NanezDataStore.getInstance().getValue(NanezDataConst.KEY_USER_PASSWORD, NanezDataStore.ValueType.StringValue("")).firstOrNull()
        return UserLoginDTO(email ?: "", password ?: "")
    }

    suspend fun saveUserLoginInfo(email: String, password: String) {
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_EMAIL, NanezDataStore.ValueType.StringValue(email))
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_PASSWORD, NanezDataStore.ValueType.StringValue(password))
    }
}