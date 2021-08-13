package com.natife.trainee.core.utils

import java.lang.Exception

sealed class Result <T: Any> {

    data class Success<T : Any>(val data: T) : Result<T>()

    data class Error<T : Any>(val error: Exception) : Result<T>()

}