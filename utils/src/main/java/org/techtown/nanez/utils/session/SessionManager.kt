package org.techtown.nanez.utils.session

import org.techtown.nanez.utils.NanezDataStore
import org.techtown.nanez.utils.datastore.NanezDataConst

/**
 * Created by haul on 12/21/23
 */
class SessionManager private constructor() {

    companion object {
        val instance: SessionManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { SessionManager() }
    }

    private var userEmail: String? = null
    private var accessToken: String? = null
    private var refreshToken: String? = null
    private var password: String? = null


    fun isLoginCheck() = accessToken?.isNotEmpty() == true && refreshToken?.isNotEmpty() == true

    fun saveEmail(email: String) {
        userEmail = email
    }

    fun savePassword(password: String) {
        this.password = password
    }

    fun saveToken(accessToken: String, refreshToken: String) {
        this.accessToken = accessToken
        this.refreshToken = refreshToken
    }

    fun getAccessToken() = accessToken

    fun getUserEmail(): String? {
        return userEmail
    }

    fun getPassWord() = password


    suspend fun logOut() {
        NanezDataStore.getInstance().clearValue(NanezDataConst.KEY_USER_EMAIL, NanezDataStore.ValueType.StringValue(""))
        NanezDataStore.getInstance().clearValue(NanezDataConst.KEY_USER_PASSWORD, NanezDataStore.ValueType.StringValue(""))
        NanezDataStore.getInstance().clearValue(NanezDataConst.KEY_USER_ACCESS_TOKEN, NanezDataStore.ValueType.StringValue(""))
        NanezDataStore.getInstance().clearValue(NanezDataConst.KEY_USER_REFRESH_TOKEN, NanezDataStore.ValueType.StringValue(""))

        userEmail = null
        accessToken = null
        refreshToken = null
    }
}