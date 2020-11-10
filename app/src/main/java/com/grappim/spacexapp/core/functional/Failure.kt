package com.grappim.spacexapp.core.functional

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific onFailure should extend [FeatureFailure] class.
 */

const val FAILURE_NETWORK_CONNECTION_ERROR = "Network Connection Error"
const val FAILURE_SERVER_ERROR = "Server Error"

sealed class Failure {
  object NetworkConnection : Failure()
  object ServerError : Failure()

  /** * Extend this class for feature specific failures.*/
  abstract class FeatureFailure : Failure()
}
