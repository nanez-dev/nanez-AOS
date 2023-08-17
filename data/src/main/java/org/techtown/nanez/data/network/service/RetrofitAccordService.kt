package org.techtown.nanez.data.network.service

import org.techtown.nanez.data.api.users.EmailVerifyApi
import org.techtown.nanez.data.api.users.SignInApi
import org.techtown.nanez.data.api.users.SignUpApi
import org.techtown.nanez.data.api.users.ValidationEmailApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by iseungjun on 2023/08/17
 */
interface RetrofitAccordService {

    @GET("api/accord")
    fun getAllAccord(): Response<String>

}