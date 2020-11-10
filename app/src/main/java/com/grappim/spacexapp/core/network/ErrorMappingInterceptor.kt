package com.grappim.spacexapp.core.network

import com.google.gson.Gson
import com.grappim.spacexapp.api.BaseApiError
import com.grappim.spacexapp.core.exception.NetworkException
import com.grappim.spacexapp.di.scopes.AppScope
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

@AppScope
class ErrorMappingInterceptor @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val gson: Gson
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        try {
            if (!networkHandler.isConnected) throw  NetworkException(NetworkException.ERROR_NO_INTERNET)
            val request = chain.request()
            val response = chain.proceed(request)
            if (!response.isSuccessful) {
                throw mapErrorBodyToException(response)
            } else {
                response
            }
        } catch (throwable: Throwable) {
            throw throwable.mapNetworkException()
        }

    private fun mapErrorBodyToException(response: Response): Throwable {
        val apiError = gson.fromJson(
            response.body?.string(),
            BaseApiError::class.java
        )
        return NetworkException(
            errorCode = NetworkException.ERROR_API,
            request = "[Request]: ${response.request.method} ${response.request.url}"
        )
    }

    private fun Throwable.mapNetworkException(): Throwable =
        when (this) {
            is NetworkException -> this
            is IOException -> NetworkException(NetworkException.ERROR_NETWORK_IO, this)
            is SocketTimeoutException -> NetworkException(NetworkException.ERROR_TIMEOUT, this)
            else -> NetworkException(NetworkException.ERROR_UNDEFINED, this)
        }
}