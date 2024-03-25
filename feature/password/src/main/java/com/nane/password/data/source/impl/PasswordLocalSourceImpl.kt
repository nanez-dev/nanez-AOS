package com.nane.password.data.source.impl

import com.nane.password.data.source.IPasswordLocalSource
import org.techtown.nanez.utils.NanezDataStore
import org.techtown.nanez.utils.datastore.NanezDataConst
import javax.inject.Inject

/**
 * Created by haul on 3/24/24
 */
class PasswordLocalSourceImpl @Inject constructor() : IPasswordLocalSource {

    override suspend fun clearUserLoginInfo() {
        NanezDataStore.getInstance().clearValue(NanezDataConst.KEY_USER_EMAIL, NanezDataStore.ValueType.StringValue(""))
        NanezDataStore.getInstance().clearValue(NanezDataConst.KEY_USER_PASSWORD, NanezDataStore.ValueType.StringValue(""))
        NanezDataStore.getInstance().clearValue(NanezDataConst.KEY_USER_ACCESS_TOKEN, NanezDataStore.ValueType.StringValue(""))
        NanezDataStore.getInstance().clearValue(NanezDataConst.KEY_USER_REFRESH_TOKEN, NanezDataStore.ValueType.StringValue(""))
    }
}