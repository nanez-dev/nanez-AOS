package com.nane.network

import okhttp3.Interceptor
import okhttp3.Response
import org.techtown.nanez.utils.session.SessionManager
import java.io.IOException

/**
 * Created by haul on 12/21/23
 */
class RetrofitHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().apply {
            if (SessionManager.instance.isLoginCheck()) {
                val token = SessionManager.instance.getAccessToken()
                if (token?.isNotEmpty() == true) {
                    header(API_AUTH_TOKEN, "Bearer $token")
                }
            }
        }

        return chain.proceed(request.build())
    }

    companion object {
        private const val API_AUTH_TOKEN = "Authorization"
    }
}