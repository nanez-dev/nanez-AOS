package com.nane.network.parser

import com.nane.network.api.FailedResponse
import org.json.JSONObject
import org.techtown.nanez.utils.NaneLog
import retrofit2.Response

/**
 * Created by haul on 2023/10/24
 */

fun <T> getParseErrorResult(response: Response<T>?): FailedResponse {
    return try {
        val jsonObject = response?.errorBody()?.string()?.let { JSONObject(it) }
        val failedResponse = FailedResponse(
            errorCode = response?.code() ?: 0,
            errorMsg = jsonObject?.getString("detail")
        )
        failedResponse
    } catch (e: Exception) {
        NaneLog.e(e)
        FailedResponse(0, null)
    }
}