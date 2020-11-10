package com.grappim.spacexapp.core.network

import com.grappim.spacexapp.core.functional.Either
import com.grappim.spacexapp.core.functional.Failure
import retrofit2.Response
import timber.log.Timber

interface NetworkHelper {

  fun <T> makeRequest(
    callResponse: Response<T>,
    default: T
  ): Either<Failure, T> {
    Timber.d("makeRequest")
    return try {
      when (callResponse.isSuccessful) {
        true -> Either.Right(callResponse.body() ?: default)
        false -> Either.Left(Failure.ServerError)
      }
    } catch (exception: Throwable) {
      Either.Left(Failure.ServerError)
    }
  }

}