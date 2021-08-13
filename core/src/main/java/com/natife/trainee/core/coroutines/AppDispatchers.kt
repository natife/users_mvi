package com.natife.trainee.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {

    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher

}