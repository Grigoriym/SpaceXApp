package com.grappim.spacexapp.data.remote

import com.grappim.spacexapp.util.Either
import retrofit2.Call

open class BaseRepository {
    suspend fun <T> apiCall(call: suspend () -> T): Either<Throwable, T> =
        try {
            Either.Right(call.invoke())
        } catch (throwable: Throwable) {
            Either.Left(throwable)
        }

    fun <T> apiCallSync(call: () -> Call<T>): Either<Throwable, T> =
        try {
            Either.Right(call.invoke().execute().body()!!)
        } catch (throwable: Throwable) {
            Either.Left(throwable)
        }
}