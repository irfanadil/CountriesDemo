package com.demo.countriesdemo.model

sealed class ApiResponse<out T> {
    object Loading: ApiResponse<Nothing>()
    data class Success<T>(val data:T): ApiResponse<T>()
    data class Error<T>(val message: String, val exception: Exception?=null): ApiResponse<T>()
}