package com.example.kakaomappath.api.common

import retrofit2.Response

suspend fun <T> safeApiCall(
    call: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = call()
        if (response.isSuccessful && response.body() != null) {
            ApiResult.Success(data = response.body()!!)
        } else {
            ApiResult.Error(
                code = response.code(),
                errorMsg = response.errorBody()?.string(),
                apiName = response.raw().request.url.toString()
            )
        }
    } catch (e: Exception) {
        ApiResult.Error(
            errorMsg = "예외 발생 : ${e.message}",
        )
    }
}

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(
        val code: Int? = null,
        val errorMsg: String? = null,
        val apiName: String? = null
    ) : ApiResult<Nothing>()
}