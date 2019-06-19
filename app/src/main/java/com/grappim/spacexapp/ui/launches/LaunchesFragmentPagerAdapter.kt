package com.grappim.spacexapp.ui.launches

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.grappim.spacexapp.ui.launches.completed.CompletedLaunchesFragment
import com.grappim.spacexapp.ui.launches.upcoming.UpcomingLaunchesFragment

class LaunchesFragmentPagerAdapter(
  fragmentManager: FragmentManager?
) : FragmentStatePagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  override fun getItem(position: Int): Fragment {
    return when (position) {
      0 -> UpcomingLaunchesFragment()
      else -> CompletedLaunchesFragment()
    }
  }

  override fun getCount(): Int = 2

  override fun getPageTitle(position: Int): CharSequence? {
    return when (position) {
      0 -> "Upcoming"
      else -> "Completed"
    }
  }
}