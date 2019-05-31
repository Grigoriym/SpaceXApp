package com.grappim.spacexapp.ui.twitter

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.TwitterApi
import com.grappim.spacexapp.pagination.TwitterDataSourceFactory
import com.grappim.spacexapp.pagination.TwitterPaginationRepository
import com.grappim.spacexapp.repository.TwitterRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class TwitterViewModel(
  private val repository: TwitterRepository,
  private val paginationRepo: TwitterPaginationRepository,
  private val api: TwitterApi
) : ViewModel(), LifecycleObserver {

  private val sourceFactory: TwitterDataSourceFactory = TwitterDataSourceFactory(api)
  lateinit var newTimelines: LiveData<PagedList<UserTimelineModel>>

  init {
    val config = PagedList.Config.Builder()
      .setPageSize(30)
      .setEnablePlaceholders(false)
      .setPrefetchDistance(10)
      .build()
    newTimelines = LivePagedListBuilder<Long, UserTimelineModel>(sourceFactory,config).build()
  }

  private val _userTimeline = MutableLiveData<Response<List<UserTimelineModel>>>()
  val userTimeline: LiveData<Response<List<UserTimelineModel>>>
    get() = _userTimeline

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getUserTimeline() {
    Timber.d("TwitterViewModel - getUserTimeline")
    viewModelScope.launch {
      _userTimeline.value = repository.getUserTimelineFromApi().value
    }
  }

  private val _testUserTimeline = MutableLiveData<PagedList<UserTimelineModel>>()
  val testUserTimeline: LiveData<PagedList<UserTimelineModel>>
    get() = _testUserTimeline

  fun getTestUserTimeline() {
    Timber.d("TwitterViewModel - getUserTimelineModel")
    _testUserTimeline.value = paginationRepo.timelines().value
  }
}