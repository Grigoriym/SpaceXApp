package com.grappim.spacexapp.ui.social_media.reddit.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.grappim.spacexapp.api.RedditApi
import com.grappim.spacexapp.api.model.reddit.RedditChildren
import com.grappim.spacexapp.di.qualifiers.RedditApiQualifier
import com.grappim.spacexapp.di.scopes.AppScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@AppScope
class RedditRepositoryImpl @Inject constructor(
    @RedditApiQualifier private val redditApi: RedditApi
) : RedditRepository {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 30
        private const val DEFAULT_PREFETCH_DISTANCE = 20
    }

    override fun getPostsBySubreddit(subReddit: String): Flow<PagingData<RedditChildren>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = DEFAULT_PREFETCH_DISTANCE
            ),
            pagingSourceFactory = {
                RedditPagingSource(redditApi, subReddit)
            }
        ).flow
    }

}