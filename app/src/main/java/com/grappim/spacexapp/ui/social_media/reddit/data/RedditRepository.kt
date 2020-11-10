package com.grappim.spacexapp.ui.social_media.reddit.data

import androidx.paging.PagingData
import com.grappim.spacexapp.api.model.reddit.RedditChildren
import kotlinx.coroutines.flow.Flow

interface RedditRepository {

    fun getPostsBySubreddit(subReddit: String): Flow<PagingData<RedditChildren>>
}