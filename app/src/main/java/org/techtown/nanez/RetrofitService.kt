package org.techtown.nanez

import android.content.SharedPreferences
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.*
import retrofit2.Call

lateinit var sharedPreferences: SharedPreferences

interface RetrofitService {
    @POST("api/users/email-send")
    fun nanezEmail(
        @Body params: HashMap<String, Any>
    ): Call<String>
}