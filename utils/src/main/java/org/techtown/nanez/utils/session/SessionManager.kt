package org.techtown.nanez.utils.session

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


    fun isLoginCheck() = accessToken?.isNotEmpty() == true && refreshToken?.isNotEmpty() == true

    fun saveEmail(email: String) {
        userEmail = email
    }

    fun saveToken(accessToken: String, refreshToken: String) {
        this.accessToken = accessToken
        this.refreshToken = refreshToken
    }

    fun getAccessToken() = accessToken

    fun getUserEmail(): String? {
        return userEmail
    }


    fun logOut() {
        userEmail = null
        accessToken = null
        refreshToken = null
    }
}