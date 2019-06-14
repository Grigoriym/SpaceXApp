package com.grappim.spacexapp.ui.social_media.reddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.grappim.spacexapp.R
import com.grappim.spacexapp.ui.base.BaseFragment

class RedditFragment : BaseFragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_reddit, container, false)
  }

  override fun renderFailure(failureText: String) {
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(true)
  }
}
