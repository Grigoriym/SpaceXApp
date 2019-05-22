package com.grappim.spacexapp.ui.twitter

import androidx.lifecycle.*
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.repository.TwitterRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class TwitterViewModel(
  private val repository: TwitterRepository
) : ViewModel(), LifecycleObserver {

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
}