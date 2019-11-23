package com.grappim.spacexapp.ui.social_media

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.grappim.spacexapp.ui.social_media.reddit.RedditFragment
import com.grappim.spacexapp.ui.social_media.twitter.TwitterFragment

class SocialMediaFragmentPagerAdapter(
  fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  override fun getItem(position: Int): Fragment {
    return when (position) {
      0 -> TwitterFragment()
      else -> RedditFragment()
    }
  }

  override fun getCount(): Int = 2

  override fun getPageTitle(position: Int): CharSequence? {
    return when (position) {
      0 -> "Twitter"
      else -> "Reddit"
    }
  }
}