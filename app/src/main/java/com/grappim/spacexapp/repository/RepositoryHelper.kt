package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Deferred

interface RepositoryHelper {

  suspend fun <T> generalRequest(liveData: Deferred<T>): LiveData<T>
}