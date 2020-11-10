package com.grappim.spacexapp.ui.social_media.twitter.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.grappim.spacexapp.api.TwitterApi
import com.grappim.spacexapp.di.qualifiers.TwitterApiQualifier
import com.grappim.spacexapp.di.scopes.AppScope
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.ui.social_media.twitter.TwitterPagingSource
import com.grappim.spacexapp.ui.social_media.twitter.domain.TwitterPaginationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@AppScope
class TwitterPaginationRepositoryImpl @Inject constructor(
    @TwitterApiQualifier private val twitterApi: TwitterApi
) : TwitterPaginationRepository {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 100
    }

    override fun getSearchResult(screenName: String): Flow<PagingData<UserTimelineModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TwitterPagingSource(twitterApi, screenName)
            }
        ).flow
    }
}