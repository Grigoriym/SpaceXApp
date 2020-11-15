package com.grappim.spacexapp.ui.launches

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grappim.spacexapp.ui.launches.completed.CompletedLaunchesFragment
import com.grappim.spacexapp.ui.launches.upcoming.UpcomingLaunchesFragment

class LaunchesFragmentPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> UpcomingLaunchesFragment()
            else -> CompletedLaunchesFragment()
        }
}