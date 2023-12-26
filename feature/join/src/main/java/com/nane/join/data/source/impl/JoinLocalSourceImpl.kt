package com.nane.join.data.source.impl

import com.nane.join.data.source.IJoinLocalSource
import org.techtown.nanez.utils.NanezDataStore
import org.techtown.nanez.utils.datastore.NanezDataConst
import javax.inject.Inject

/**
 * Created by haul on 11/30/23
 */
class JoinLocalSourceImpl @Inject constructor() : IJoinLocalSource {

    override suspend fun saveUserToken(accessToken: String?, refreshToken: String?) {
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_ACCESS_TOKEN, NanezDataStore.ValueType.StringValue(accessToken ?: ""))
        NanezDataStore.getInstance().setValue(NanezDataConst.KEY_USER_REFRESH_TOKEN, NanezDataStore.ValueType.StringValue(refreshToken ?: ""))
    }
}