package com.example.pokedex_mvvm.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber


suspend fun <T, R> T.runCatching(
    dispatcher: CoroutineDispatcher,
    execute: suspend () -> R,
    onSuccess: (R) -> Unit = {},
    onFailure: (Throwable) -> Unit = {}
) {
    runCatching {
        withContext(dispatcher) {
            execute()
        }
    }.onSuccess(onSuccess)
        .onFailure {
            Timber.e(it)
            onFailure(it)
        }
}

suspend fun <T, R> T.runCatchingWithFlow(

        execute: suspend () -> R,
        onSuccess: suspend (R) -> Unit = {},
        onFailure: suspend (Throwable) -> Unit = {}
) {
    runCatching {
            execute()
    }.onSuccess {
        onSuccess(it)
    }
            .onFailure {
                Timber.e(it)
                onFailure(it)
            }
}