package com.grappim.spacexapp.ui.social_media

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grappim.spacexapp.ui.social_media.reddit.RedditFragment
import com.grappim.spacexapp.ui.social_media.twitter.TwitterFragment

class SocialMediaFragmentPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> TwitterFragment()
            else -> RedditFragment()
        }
}