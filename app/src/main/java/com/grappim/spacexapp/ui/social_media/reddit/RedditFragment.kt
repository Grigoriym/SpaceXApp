package com.grappim.spacexapp.ui.social_media.reddit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.grappim.spacexapp.R
import com.grappim.spacexapp.ui.SharedFragment

class RedditFragment : SharedFragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_reddit, container, false)
  }


}
