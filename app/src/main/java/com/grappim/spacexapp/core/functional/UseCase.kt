package com.grappim.spacexapp.core.functional

import kotlinx.coroutines.*

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

  private var parentJob: Job? = null

  abstract suspend fun run(params: Params): Either<Throwable, Type>

  operator fun invoke(params: Params, onResult: (Either<Throwable, Type>) -> Unit = {}) {
    val job = CoroutineScope(Dispatchers.IO).async { run(params) }
    parentJob = CoroutineScope(Dispatchers.Main).launch { onResult(job.await()) }
  }

  fun unBind() {
    parentJob?.cancel()
  }

  class None
}
