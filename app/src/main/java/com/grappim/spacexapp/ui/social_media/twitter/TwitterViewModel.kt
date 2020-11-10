package com.grappim.spacexapp.ui.social_media.twitter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.ui.social_media.twitter.domain.TwitterPaginationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TwitterViewModel @Inject constructor(
    private val repository: TwitterPaginationRepository
) : ViewModel() {

    private var currentScreenName: String? = null
    private var currentSearchResult: Flow<PagingData<UserTimelineModel>>? = null

    fun search(screenName: String): Flow<PagingData<UserTimelineModel>> {
        val lastResult = currentSearchResult
        if (screenName == currentScreenName && lastResult != null) {
            return lastResult
        }
        currentScreenName = screenName
        val newResult: Flow<PagingData<UserTimelineModel>> =
            repository.getSearchResult(screenName)
                .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

//    private val _currentScreenName = MutableLiveData<String>()
//    val currentScreenName: LiveData<String>
//        get() = _currentScreenName
//
//    private val repoResult = map(_currentScreenName) {
//        repository.getTweets(it)
//    }
//    val tweets = switchMap(repoResult) { it.pagedList }
//    val networkState = switchMap(repoResult) { it.networkState }
//
//    fun refresh() {
//        Timber.d("TwitterViewModel - refresh")
//        repoResult.value?.refresh?.invoke()
//    }
//
//    fun setCurrentScreenName(name: String) {
//        _currentScreenName.value = name
//    }
//
//    fun showTweets() {
//        repository.getTweets(_currentScreenName.value ?: "")
//    }
}