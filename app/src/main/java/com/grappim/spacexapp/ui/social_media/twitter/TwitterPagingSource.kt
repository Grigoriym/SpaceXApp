package com.grappim.spacexapp.ui.social_media.twitter

import androidx.paging.PagingSource
import com.grappim.spacexapp.api.TwitterApi
import com.grappim.spacexapp.model.twitter.UserTimelineModel

private const val STARTING_PAGE_INDEX = 1

class TwitterPagingSource(
    private val service: TwitterApi,
    private val screenName: String
) : PagingSource<Int, UserTimelineModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserTimelineModel> {
        val position = params.key ?: STARTING_PAGE_INDEX

        val response = service.getUserTimelineAsync(
            screenName = screenName,
            page = position,
            count = params.loadSize
        )
        return try {
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}