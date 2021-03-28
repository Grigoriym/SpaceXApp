package com.grappim.spacexapp.ui.social_media.twitter.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.grappim.spacexapp.api.TwitterApi
import com.grappim.spacexapp.api.model.twitter.TweetModel

class TwitterPagingSource(
    private val service: TwitterApi,
    private val screenName: String
) : PagingSource<Int, TweetModel>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TweetModel> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = service.getUserTimelineAsync(
                screenName = screenName,
                page = position,
                count = params.loadSize
            )

            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TweetModel>): Int? = null

}