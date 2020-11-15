package com.grappim.spacexapp.ui.social_media.twitter.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.grappim.spacexapp.api.TwitterApi
import com.grappim.spacexapp.api.model.twitter.TweetModel
import com.grappim.spacexapp.di.qualifiers.TwitterApiQualifier
import com.grappim.spacexapp.di.scopes.AppScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@AppScope
class TwitterPaginationRepositoryImpl @Inject constructor(
    @TwitterApiQualifier private val twitterApi: TwitterApi
) : TwitterPaginationRepository {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 50
        private const val DEFAULT_PREFETCH_DISTANCE = 30
    }

    override fun getSearchResult(screenName: String): Flow<PagingData<TweetModel>> =
        Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = DEFAULT_PREFETCH_DISTANCE
            ),
            pagingSourceFactory = { TwitterPagingSource(twitterApi, screenName) }
        ).flow

}