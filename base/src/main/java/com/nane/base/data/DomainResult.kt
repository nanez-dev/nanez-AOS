package com.nane.base.data

import java.lang.Exception

/**
 * Created by iseungjun on 2023/08/26
 */
sealed class DomainResult<out R> {
    class Success<out T>(val data: T) : DomainResult<T>()
    class Failed(val msg: String?, val code: Int) : DomainResult<Nothing>()
    class Error(val exception: Exception) : DomainResult<Nothing>()
}