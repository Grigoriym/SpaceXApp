package com.grappim.spacexapp.ui.launches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.grappim.spacexapp.R
import kotlinx.android.synthetic.main.fragment_launches.tlLaunches
import kotlinx.android.synthetic.main.fragment_launches.vpLaunches
import timber.log.Timber

class LaunchesFragment : Fragment(R.layout.fragment_launches) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("LaunchesFragment - onViewCreated")
        val lfpa = LaunchesFragmentPagerAdapter(this)
        vpLaunches.adapter = lfpa
        TabLayoutMediator(tlLaunches, vpLaunches) { tab, position ->
            tab.text = when (position) {
                0 -> "Upcoming"
                else -> "Completed"
            }
        }.attach()
    }

}
