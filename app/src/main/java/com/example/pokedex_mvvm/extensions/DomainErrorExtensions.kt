package com.example.pokedex_mvvm.extensions

import com.example.pokedex_mvvm.utils.AuthenticationException
import com.example.pokedex_mvvm.utils.UnexpectedException
import retrofit2.HttpException as RetrofitHttpException

internal fun <T> Result<T>.getOrThrowDomainError(): T {
    return getOrElse { throw it.toDomainError() }
}

internal fun Throwable.toDomainError(): Throwable {
    return when (this) {
        is RetrofitHttpException -> {
            when (code()) {
                401 -> AuthenticationException
                else -> UnexpectedException(message())
            }
        }
        else -> this
    }
}
