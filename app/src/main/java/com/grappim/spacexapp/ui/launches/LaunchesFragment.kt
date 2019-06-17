package com.grappim.spacexapp.ui.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.grappim.spacexapp.R
import kotlinx.android.synthetic.main.fragment_launches.*
import timber.log.Timber

class LaunchesFragment : Fragment () {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_launches, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("LaunchesFragment - onViewCreated")
    val lfpa = LaunchesFragmentPagerAdapter(activity?.supportFragmentManager)
    vpLaunches.adapter = lfpa
    tlLaunches.setupWithViewPager(vpLaunches)
  }

}
