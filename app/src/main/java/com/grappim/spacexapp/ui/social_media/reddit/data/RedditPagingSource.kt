package com.grappim.spacexapp.ui.social_media.reddit.data

import androidx.paging.PagingSource
import com.grappim.spacexapp.api.RedditApi
import com.grappim.spacexapp.api.model.reddit.RedditChildren

private const val STARTING_PAGE_INDEX = 1

class RedditPagingSource(
    private val service: RedditApi,
    private val subReddit: String
) : PagingSource<Int, RedditChildren>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RedditChildren> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getPostsBySubreddit(
                subreddit = subReddit,
                limit = params.loadSize
            )

            LoadResult.Page(
                data = response.data.children,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.data.children.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}