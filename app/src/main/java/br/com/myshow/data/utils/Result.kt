package br.com.myshow.data.utils

import retrofit2.Response
import java.net.HttpURLConnection

sealed class Result<out R> {
    data class Success<out T>(val value: T?) : Result<T>()
    data class Failure(val statusCode: Int) : Result<Nothing>()
}

fun <R : Any> Response<R>.parseResponse(): Result<R> {
    if (isSuccessful) {
        val body = body()

        if (body != null) {
            return Result.Success(body)
        }
    } else {
        return Result.Failure(code())

    }
    return Result.Failure(HttpURLConnection.HTTP_INTERNAL_ERROR)
}