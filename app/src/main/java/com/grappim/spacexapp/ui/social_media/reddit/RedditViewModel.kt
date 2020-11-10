package com.grappim.spacexapp.ui.social_media.reddit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.grappim.spacexapp.api.model.reddit.RedditChildren
import com.grappim.spacexapp.ui.social_media.reddit.data.RedditRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RedditViewModel @Inject constructor(
    private val repository: RedditRepository
) : ViewModel() {

    private var currentSubreddit: String? = null
    private var currentSearchResult: Flow<PagingData<RedditChildren>>? = null

    fun search(subreddit: String): Flow<PagingData<RedditChildren>> {
        val lastResult = currentSearchResult
        if (subreddit == currentSubreddit && lastResult != null) {
            return lastResult
        }
        currentSubreddit = subreddit
        val newResult: Flow<PagingData<RedditChildren>> =
            repository.getPostsBySubreddit(subreddit)
                .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}