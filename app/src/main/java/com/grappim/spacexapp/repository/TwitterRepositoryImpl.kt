package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.TwitterApi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TwitterRepositoryImpl(
  val api: TwitterApi
) : TwitterRepository {

  private suspend fun <T> generalRequest(liveData: Deferred<T>): LiveData<T> {
    val data: MutableLiveData<T> = MutableLiveData()
    withContext(Dispatchers.IO) {
      val response = liveData.await()
      withContext(Dispatchers.Main) {
        data.value = response
      }
    }
    return data
  }

  override suspend fun getUserTimelineFromApi(): LiveData<Response<List<UserTimelineModel>>> =
    generalRequest(api.getUserTimeline())
}