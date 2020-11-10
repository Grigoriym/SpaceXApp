package com.grappim.spacexapp.core.functional

sealed class Resource<out Data> {
    object Loading: Resource<Nothing>()
    object Empty: Resource<Nothing>()
    data class Error(val exception: Throwable): Resource<Nothing>()
    data class Success<out Data>(val data: Data): Resource<Data>()
}