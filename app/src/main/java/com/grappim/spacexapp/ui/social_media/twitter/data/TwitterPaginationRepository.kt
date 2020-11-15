package com.grappim.spacexapp.ui.social_media.twitter.data

import androidx.paging.PagingData
import com.grappim.spacexapp.api.model.twitter.TweetModel
import kotlinx.coroutines.flow.Flow

interface TwitterPaginationRepository {

    fun getSearchResult(screenName: String): Flow<PagingData<TweetModel>>
}