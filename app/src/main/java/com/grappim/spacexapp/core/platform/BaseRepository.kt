package com.grappim.spacexapp.core.platform

import com.grappim.spacexapp.util.Either
import retrofit2.Call
import timber.log.Timber

open class BaseRepository {

    protected suspend fun <T : Any> apiCall(call: suspend () -> T): Either<Throwable, T> =
        try {
            Either.Right(call.invoke())
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            Either.Left(throwable)
        }

    protected fun <T : Any> apiCallSync(call: () -> Call<T>): Either<Throwable, T> =
        try {
            Either.Right(call.invoke().execute().body()!!)
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            Either.Left(throwable)
        }

}