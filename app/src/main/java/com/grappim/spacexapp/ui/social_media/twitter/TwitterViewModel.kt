package com.grappim.spacexapp.ui.social_media.twitter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.grappim.spacexapp.api.model.twitter.TweetModel
import com.grappim.spacexapp.ui.social_media.twitter.data.TwitterPaginationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TwitterViewModel @Inject constructor(
    private val repository: TwitterPaginationRepository
) : ViewModel() {

    private var currentScreenName: String? = null
    private var currentSearchResult: Flow<PagingData<TweetModel>>? = null

    fun search(screenName: String): Flow<PagingData<TweetModel>> {
        val lastResult = currentSearchResult
        if (screenName == currentScreenName && lastResult != null) {
            return lastResult
        }
        currentScreenName = screenName
        val newResult: Flow<PagingData<TweetModel>> =
            repository.getSearchResult(screenName)
                .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}