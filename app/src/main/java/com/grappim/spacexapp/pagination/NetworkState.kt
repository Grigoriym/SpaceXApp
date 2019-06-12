package com.grappim.spacexapp.pagination

enum class Status {
  RUNNING,
  SUCCESS,
  FAILED
}

data class NetworkState private constructor(
  val status: Status,
  val msg: String? = null
) {
  companion object {
    val LOADED = NetworkState(Status.SUCCESS)
    val LOADING = NetworkState(Status.RUNNING)
    val ERROR = NetworkState(Status.FAILED)
    fun error(msg: String) = NetworkState(Status.FAILED, msg)
  }
}