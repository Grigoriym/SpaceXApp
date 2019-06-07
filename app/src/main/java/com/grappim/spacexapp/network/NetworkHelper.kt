package com.grappim.spacexapp.network

import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import retrofit2.Response
import timber.log.Timber

abstract class NetworkHelper {

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