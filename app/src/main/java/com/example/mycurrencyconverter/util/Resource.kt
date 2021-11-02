package com.example.mycurrencyconverter.util

sealed class Resource<T>(
    val body: T? = null,
    val message: String? = null
) {
    class Success<T>(body: T) : Resource<T>(body, null)
    class Error<T>(message: String): Resource<T>(null, message)
    class Loading<T>: Resource<T>()
}