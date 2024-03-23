package com.nane.base.data

/**
 * Created by iseungjun on 2023/08/26
 */
sealed class DataResult<out R> {

    class Success<out T>(val data: T) : DataResult<T>()
    class Failed(val msg: String?, val code: Int) : DataResult<Nothing>()
    class Error(val exception: Exception) : DataResult<Nothing>()
}