package com.grappim.spacexapp.pagination

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
  val pagedList: LiveData<PagedList<T>>,
  val networkState: LiveData<NetworkState>,
  val refreshState: LiveData<NetworkState>,
  val refresh: () -> Unit,
  val retry: () -> Unit
)