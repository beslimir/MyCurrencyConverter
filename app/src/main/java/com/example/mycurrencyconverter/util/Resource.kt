package com.example.mycurrencyconverter.util

sealed class Resource<T>(
    val message: String? = null
) {
    class Success<T>(message: String) : Resource<T>(message)
    class Error<T>(message: String): Resource<T>(message)
    class Loading<T>: Resource<T>()
}