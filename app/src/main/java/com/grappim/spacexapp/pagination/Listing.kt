package com.grappim.spacexapp.pagination

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.grappim.spacexapp.util.Failure

data class Listing<T : Any>(
  val pagedList: LiveData<PagedList<T>>,
  val networkState: LiveData<NetworkState>,
  val refresh: () -> Unit,
  val failure: LiveData<Failure>
)