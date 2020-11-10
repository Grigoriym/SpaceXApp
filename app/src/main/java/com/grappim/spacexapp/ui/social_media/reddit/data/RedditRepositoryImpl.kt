package com.grappim.spacexapp.ui.social_media.reddit.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.grappim.spacexapp.api.RedditApi
import com.grappim.spacexapp.di.qualifiers.RedditApiQualifier
import com.grappim.spacexapp.di.scopes.AppScope
import com.grappim.spacexapp.api.model.reddit.RedditChildren
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@AppScope
class RedditRepositoryImpl @Inject constructor(
    @RedditApiQualifier private val redditApi: RedditApi
) : RedditRepository {

    override fun getPostsBySubreddit(subReddit: String): Flow<PagingData<RedditChildren>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                RedditPagingSource(redditApi, subReddit)
            }
        ).flow
    }

}