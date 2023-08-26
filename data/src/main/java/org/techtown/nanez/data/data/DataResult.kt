package org.techtown.nanez.data.data

import java.lang.Exception

/**
 * Created by iseungjun on 2023/08/26
 */
sealed class DataResult<out R> {

    class Success<out T>(val data: T) : DataResult<T>()
    class Failed(val msg: String?) : DataResult<Nothing>()
    class Error(val exception: Exception) : DataResult<Nothing>()
}