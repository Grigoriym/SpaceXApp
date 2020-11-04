package com.grappim.spacexapp.api

data class BaseApiError(
    val status: Int,
    val code: String,
    val message: String?,
    val developmentMessage: String?
)