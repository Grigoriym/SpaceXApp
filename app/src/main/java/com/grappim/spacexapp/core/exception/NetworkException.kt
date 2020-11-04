package com.grappim.spacexapp.core.exception

import java.io.IOException

class NetworkException(
    val errorCode: Int,
    val throwable: Throwable? = null,
    val request: String? = ""
) : IOException() {

    companion object {
        const val ERROR_NO_INTERNET = -1
        const val ERROR_TIMEOUT = -2
        const val ERROR_UNDEFINED = -3
        const val ERROR_NETWORK_IO = -4
        const val ERROR_API = -5
    }
}