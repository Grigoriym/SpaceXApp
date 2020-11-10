package com.grappim.spacexapp.ui.social_media.twitter.domain

import androidx.paging.PagingData
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import kotlinx.coroutines.flow.Flow

interface TwitterPaginationRepository {

    fun getSearchResult(screenName: String): Flow<PagingData<UserTimelineModel>>
}