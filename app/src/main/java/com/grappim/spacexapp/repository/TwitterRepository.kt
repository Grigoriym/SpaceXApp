package com.grappim.spacexapp.repository

import androidx.lifecycle.LiveData
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import retrofit2.Response

interface TwitterRepository {

  suspend fun getUserTimelineFromApi(screenName: String? = "SpaceX"): LiveData<Response<List<UserTimelineModel>>>
}